import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ManagerRoutingModule } from './manager-routing.module';
import { ManagerComponent } from './manager.component';
import { ManagerDashboardComponent } from './pages/manager-dashboard/manager-dashboard.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';
import { AllProjectsComponent } from './pages/all-projects/all-projects.component';
import { ProjectDashboardComponent } from '../basic/component/projectDashboard/project-dashboard/project-dashboard.component';

@NgModule({
  declarations: [
    ManagerComponent,
    ManagerDashboardComponent,
    CreateProjectComponent,
    AllProjectsComponent,
    ProjectDashboardComponent,
  ],
  imports: [CommonModule, ManagerRoutingModule, ReactiveFormsModule],
})
export class ManagerModule {}
