import { Component } from '@angular/core';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrl: './manager-dashboard.component.scss',
})
export class ManagerDashboardComponent {
  projects: any;
  mappedProjects: any[any] = []; // Initialise mappedProjects en tant que tableau vide

  constructor(private managerService: ManagerService) {}

  ngOnInit() {
    this.getAllProjects();
  }

  getAllProjects() {
    this.managerService.getAllProjects().subscribe((res) => {
      this.projects = res;
      console.log(res);
    });
  }
}
