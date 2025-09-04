import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Routing
import { EmployeeRoutingModule } from './employee-routing.module';

// Shared
import { SharedModule } from '../../shared/shared.module';

// Angular Material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

// PrimeNG
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { CreateTaskComponent } from './pages/create-task/create-task.component';
import { EmployeeDashboardComponent } from './pages/employee-dashboard/employee-dashboard.component';

@NgModule({
  declarations: [CreateTaskComponent, EmployeeDashboardComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    // Routing
    EmployeeRoutingModule,

    // Shared
    SharedModule,

    // Angular Material
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatButtonModule,

    // PrimeNG
    ToastModule,
    ButtonModule,
    RippleModule,
  ],
})
export class EmployeeModule {}
