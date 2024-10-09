import { Component } from '@angular/core';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-all-projects',
  templateUrl: './all-projects.component.html',
  styleUrl: './all-projects.component.scss',
})
export class AllProjectsComponent {
  projects: any;

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
