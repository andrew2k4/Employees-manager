import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ManagerService } from '../../services/manager.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.scss'],
})
export class UpdateProjectComponent implements OnInit {
  validateForm!: FormGroup;
  projectId!: number;
  project: any;

  constructor(
    private managerService: ManagerService,
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.projectId = Number(params.get('id'));
    });

    // Access the project directly from the service without refetching
    this.project = this.managerService.projectToUpdate;

    // Ensure project data is loaded before initializing the form
    if (this.project) {
      this.initializeForm();
    } else {
      console.error('Project data is not available.');
    }
  }

  initializeForm() {
    this.validateForm = this.fb.group({
      projectName: [this.project.projectName, [Validators.required]],
      clientName: [this.project.clientName, [Validators.required]],
      description: [this.project.description, [Validators.required]],
    });
  }

  onUpdate() {
    if (this.validateForm.valid) {
      const projectData = {
        projectName: this.validateForm.get('projectName')?.value,
        clientName: this.validateForm.get('clientName')?.value,
        description: this.validateForm.get('description')?.value,
      };

      this.managerService.updateProject(this.projectId, projectData).subscribe(
        (res) => {
          console.log(res);
          this.router.navigateByUrl('/manager/dashboard');
        },
        (error) => {
          console.error('Error updating project:', error);
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }
}
