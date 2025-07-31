import { NgModule, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ManagerRoutingModule } from './manager-routing.module';
import { ManagerComponent } from './manager.component';
import { ManagerDashboardComponent } from './pages/manager-dashboard/manager-dashboard.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';
import { AllProjectsComponent } from './pages/all-projects/all-projects.component';
import { ProjectDashboardComponent } from '../basic/component/project-dashboard/project-dashboard.component';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ProjectDescriptionComponent } from './pages/project-description/project-description.component';
import { PrimeNGConfig } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { BrowserModule } from '@angular/platform-browser';
import { MessageService } from 'primeng/api';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { UpdateProjectComponent } from './pages/update-project/update-project.component';

@NgModule({
  declarations: [
    ManagerComponent,
    ManagerDashboardComponent,
    CreateProjectComponent,
    AllProjectsComponent,
    ProjectDashboardComponent,
    ProjectDescriptionComponent,
    UpdateProjectComponent,
  ],
  imports: [
    ToastModule,
    ButtonModule,
    RippleModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    CommonModule,
    ManagerRoutingModule,
    ReactiveFormsModule,
    MatIconModule,
  ],
  providers: [MessageService],
})
export class ManagerModule implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
