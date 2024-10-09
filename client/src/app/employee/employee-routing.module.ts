import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeComponent } from './employee.component';
import { EmployeeDashboardComponent } from './pages/employee-dashboard/employee-dashboard.component';

const routes: Routes = [
  { path: '', component: EmployeeComponent },
  { path: 'dashboard', component: EmployeeDashboardComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmployeeRoutingModule {}
