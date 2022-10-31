import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'ellipse'
})
export class EllipsePipe implements PipeTransform {

  transform(value: string, ...args: string[]): unknown {
    if(value.length>20){
      return value.substring(0,10)+"...";
    }
    return value;
  }

}
