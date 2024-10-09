import { Component } from '@angular/core';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-all-projects',
  templateUrl: './all-projects.component.html',
  styleUrls: ['./all-projects.component.scss'],
})
export class AllProjectsComponent {
  projects: any;
  mappedProjects: any[] = []; // Initialize mappedProjects as an empty array

  constructor(private managerService: ManagerService) {}

  ngOnInit() {
    this.getAllProjects();
  }

  getAllProjects() {
    this.managerService.getAllProjects().subscribe((res) => {
      this.projects = res;
      console.log(res);

      for (let i = 0; i < this.projects.length; i++) {
        const project = {
          projectName: this.projects[i].projectName, // Optionally include project-specific data
          users: [],
        };

        for (let j = 0; j < this.projects[i].users.length; j++) {
          let taskUser: any[] = [];
          let userId = this.projects[i].users[j].id;
          let hoursTravailler = 0; // Initialize hours counter for each user

          // Loop through the tasks of the project to find tasks associated with the user
          if (this.projects[i].tasks) {
            // Check if tasks exist
            for (let k = 0; k < this.projects[i].tasks.length; k++) {
              if (this.projects[i].tasks[k].userId === userId) {
                taskUser.push(this.projects[i].tasks[k]); // Add task to the user's task list
                hoursTravailler += this.projects[i].tasks[k].hours; // Sum up hours worked
              }
            }
          }

          // Push the user and their associated tasks to the users array in the project object
          project.users.push({
            user: this.projects[i].users[j], // Current user
            tasks: taskUser, // List of tasks associated with this user
            hoursTravailler: hoursTravailler, // Total hours worked by the user
          });
        }

        // Add the constructed project with users and their tasks to mappedProjects
        this.mappedProjects.push(project);
      }

      console.log(this.mappedProjects); // Output the mapped result to the console
    });
  }
}
