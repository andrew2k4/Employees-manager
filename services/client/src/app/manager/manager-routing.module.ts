import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManagerComponent } from './manager.component';
import { ManagerDashboardComponent } from './pages/manager-dashboard/manager-dashboard.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';
import { AllProjectsComponent } from './pages/all-projects/all-projects.component';
import { ProjectDescriptionComponent } from './pages/project-description/project-description.component';
import { UpdateProjectComponent } from './pages/update-project/update-project.component';

const routes: Routes = [
  { path: '', component: ManagerComponent },
  { path: 'dashboard', component: ManagerDashboardComponent },
  { path: 'project', component: CreateProjectComponent },
  { path: 'projects', component: AllProjectsComponent },
  { path: 'project/:id', component: ProjectDescriptionComponent },
  { path: 'projectt/update/:id', component: UpdateProjectComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ManagerRoutingModule {}
