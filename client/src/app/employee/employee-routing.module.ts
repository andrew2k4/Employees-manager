import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeComponent } from './employee.component';
import { EmployeeDashboardComponent } from './pages/employee-dashboard/employee-dashboard.component';
import { CreateTaskComponent } from './pages/create-task/create-task.component';

const routes: Routes = [
  { path: '', component: EmployeeComponent },
  { path: 'dashboard', component: EmployeeDashboardComponent },
  { path: 'task', component: CreateTaskComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmployeeRoutingModule {}
