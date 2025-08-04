import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FilterDashboardComponent } from './components/filter-dashboard/filter-dashboard.component';
import { ProjectDashboardComponent } from './components/project-dashboard/project-dashboard.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [FilterDashboardComponent, ProjectDashboardComponent],
  imports: [CommonModule, DatePipe, RouterModule],
  exports: [FilterDashboardComponent, ProjectDashboardComponent],
})
export class SharedModule {}
