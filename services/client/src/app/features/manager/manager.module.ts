import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

// Routing
import { ManagerRoutingModule } from './manager-routing.module';

// Shared
import { SharedModule } from '../../shared/shared.module';

// Components
import { AllProjectsComponent } from './pages/all-projects/all-projects.component';
import { ManagerDashboardComponent } from './pages/manager-dashboard/manager-dashboard.component';
import { ProjectDescriptionComponent } from './pages/project-description/project-description.component';
import { UpdateProjectComponent } from './pages/update-project/update-project.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';

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

@NgModule({
  declarations: [
    AllProjectsComponent,
    CreateProjectComponent,
    ManagerDashboardComponent,
    ProjectDescriptionComponent,
    UpdateProjectComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    // Routing
    ManagerRoutingModule,

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
export class ManagerModule {}
