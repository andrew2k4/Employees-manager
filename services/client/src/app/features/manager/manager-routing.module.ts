import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AllProjectsComponent } from './pages/all-projects/all-projects.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';
import { ManagerDashboardComponent } from './pages/manager-dashboard/manager-dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: ManagerDashboardComponent },
  { path: 'project', component: CreateProjectComponent },
  { path: 'projects', component: AllProjectsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ManagerRoutingModule {}
