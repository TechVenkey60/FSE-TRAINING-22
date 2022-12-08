package com.spring.batch.service.config;


import com.spring.batch.service.entity.Customer;
import com.spring.batch.service.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchProcessingConfig {

    private static final String CUSTOMERS_CSV_FILE_LOCATION = "data/customers.csv";

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    @Bean
    public FlatFileItemReader<Customer> reader() {
        FlatFileItemReader<Customer> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(loadCustomersCSVData());
        flatFileItemReader.setName("CSV-Customer-Info");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());

        return flatFileItemReader;
    }

    private Resource loadCustomersCSVData() {
        return new ClassPathResource(CUSTOMERS_CSV_FILE_LOCATION);
    }

    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "country", "phone");

        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


    @Bean
    public CustomerItemProcessor itemProcessor() {
        return new CustomerItemProcessor();
    }


    @Bean
    public RepositoryItemWriter<Customer> repositoryItemWriter() {
        RepositoryItemWriter<Customer> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(customerRepository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    @Bean
    public Step fistStep() {
        return stepBuilderFactory.get("CSV-First-Step")
                .<Customer, Customer>chunk(10)
                .reader(reader())
                .processor(itemProcessor())
                .writer(repositoryItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job firstJob() {
        return jobBuilderFactory.get("CSV-First-Job")
                .flow(fistStep())
                .end().build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }



}
