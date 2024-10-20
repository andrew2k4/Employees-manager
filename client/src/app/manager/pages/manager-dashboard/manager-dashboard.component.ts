import { Component } from '@angular/core';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrl: './manager-dashboard.component.scss',
})
export class ManagerDashboardComponent {
  projects: any;
  isProjectDashboardActive: boolean = true;

  constructor(private managerService: ManagerService) {}

  ngOnInit() {
    this.getAllProjects();
  }

  getAllProjects() {
    this.managerService.getAllProjects().subscribe((res) => {
      for (let project of res) {
        for (let user of project.users) {
          let workHours = 0;
          for (let task of user.tasks) {
            workHours += task.hours;
          }
          user.workHours = workHours;
        }
      }
      this.projects = res;
    });
  }

  changeDashboard(change) {
    this.isProjectDashboardActive = change;
  }
}
