import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.scss'],
})
export class CreateTaskComponent implements OnInit {
  validateForm!: FormGroup;
  projects: any[] = []; // Initialiser la liste des projets

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {
    // Récupérer les projets depuis le service
    this.employeeService.getAllProjects().subscribe(
      (res) => {
        console.log(res);
        this.projects = res; // Stocker les projets récupérés
      },
      (error) => {
        console.error('Error fetching projects', error);
      }
    );

    // Initialiser le formulaire réactif
    this.validateForm = this.fb.group({
      projectId: [null, [Validators.required]], // Dropdown pour le projet
      description: [null, [Validators.required]],
      hours: [null, [Validators.required]],
    });
  }

  postTask(): void {
    if (this.validateForm.invalid) {
      for (const i in this.validateForm.controls) {
        if (this.validateForm.controls.hasOwnProperty(i)) {
          this.validateForm.controls[i].markAsDirty();
          this.validateForm.controls[i].updateValueAndValidity();
        }
      }
      return;
    }

    const taskData = {
      projectId: this.validateForm.get('projectId')!.value, // L'ID du projet sélectionné
      description: this.validateForm.get('description')!.value,
      hours: this.validateForm.get('hours')!.value,
    };

    // Appel du service pour poster la tâche
    this.employeeService.postTask(taskData).subscribe(
      (res) => {
        console.log('Task posted successfully', res);
        this.router.navigateByUrl('/manager/dashboard');
      },
      (error) => {
        console.log(taskData);
        console.error('Error posting task', error);
      }
    );
  }
}
