package com.spring.batch.service.config;


import com.spring.batch.service.entity.Customer;
import com.spring.batch.service.partition.CustomerPartitioner;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomerWrite customerWrite;


    @Bean
    public FlatFileItemReader<Customer> reader() {
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(loadCustomerCsvFileData());
        itemReader.setName("CSVReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private Resource loadCustomerCsvFileData() {
        return new ClassPathResource("customers.csv");
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
    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }

    @Bean
    public CustomerPartitioner partitioner() {
        return new CustomerPartitioner();
    }

    @Bean
    public PartitionHandler partitionHandler() {
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
        partitionHandler.setTaskExecutor(taskExecutor());
        partitionHandler.setGridSize(4);
        partitionHandler.setStep(branchStep());
        return partitionHandler;
    }

    @Bean
    public Step branchStep() {
        return stepBuilderFactory.get("CSV-BranchStep1")
                .<Customer, Customer>chunk(250)
                .reader(reader())
                .processor(processor())
                .writer(customerWrite)
                .build();
    }

    @Bean
    public Step masterStep() {
        return stepBuilderFactory.get("CSV-MasterStep")
                .partitioner(branchStep().getName(), partitioner())
                .partitionHandler(partitionHandler())
                .build();
    }


    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("CSV-ImportCustomerJob")
                .flow(masterStep())
                .end().build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setQueueCapacity(4);
        return taskExecutor;
    }
}
