import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeRoutingModule } from './employee-routing.module';
import { EmployeeComponent } from './employee.component';
import { EmployeeDashboardComponent } from './pages/employee-dashboard/employee-dashboard.component';

@NgModule({
  declarations: [EmployeeComponent, EmployeeDashboardComponent],
  imports: [CommonModule, EmployeeRoutingModule],
})
export class EmployeeModule {}
