import { Component } from '@angular/core';
import { Input } from '@angular/core';
@Component({
  selector: 'project-dashboard',
  templateUrl: './project-dashboard.component.html',
  styleUrl: './project-dashboard.component.scss',
})
export class ProjectDashboardComponent {
  @Input() projects: any;

  getTotalHours(users: any[] = []): number {
    return users.reduce((sum, user) => sum + (user.workHours || 0), 0);
  }
}
