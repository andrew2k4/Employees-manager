import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTaskComponent } from './pages/create-task/create-task.component';
import { EmployeeDashboardComponent } from './pages/employee-dashboard/employee-dashboard.component';
import { ManagerDashboardComponent } from '../manager/pages/manager-dashboard/manager-dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: ManagerDashboardComponent },
  { path: 'task', component: CreateTaskComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmployeeRoutingModule {}
