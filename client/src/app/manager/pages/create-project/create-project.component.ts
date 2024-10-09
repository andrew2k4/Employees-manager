import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.scss'], // Corrected from `styleUrl` to `styleUrls`
})
export class CreateProjectComponent implements OnInit {
  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private managerService: ManagerService
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
