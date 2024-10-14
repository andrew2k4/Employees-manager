import { Component } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { FormBuilder, FormGroup, Validator, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ManagerService } from '../../../manager/services/manager.service';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrl: './create-task.component.scss',
})
export class CreateTaskComponent {
  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private managerService: EmployeeService
  ) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      projectName: [null, [Validators.required]],
      client: [null, [Validators.required]],
      details: [null, [Validators.required]],
    });
  }

  postProject(): void {
    if (this.validateForm.invalid) {
      for (const i in this.validateForm.controls) {
        if (this.validateForm.controls.hasOwnProperty(i)) {
          this.validateForm.controls[i].markAsDirty();
          this.validateForm.controls[i].updateValueAndValidity();
        }
      }
      return;
    }

    const formData: FormData = new FormData();
    formData.append('projectName', this.validateForm.get('projectName')!.value);
    formData.append('client', this.validateForm.get('client')!.value);
    formData.append('details', this.validateForm.get('details')!.value);

    this.managerService.postProject(formData).subscribe(
      (res) => {
        console.log('Project posted successfully', res);
        this.router.navigateByUrl('/manager/projects');
      },
      (error) => {
        console.error('Error posting project', error);
      }
    );
  }
}
