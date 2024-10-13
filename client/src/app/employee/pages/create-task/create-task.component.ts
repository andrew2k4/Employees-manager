import { Component } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrl: './create-task.component.scss',
})
export class CreateTaskComponent {
  constructor(private employeeService: EmployeeService) {}

  taskDto;
}
