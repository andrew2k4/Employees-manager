import { Component, OnInit } from '@angular/core';
import { ManagerService } from '../../services/manager.service';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-project-description',
  templateUrl: './project-description.component.html',
  styleUrl: './project-description.component.scss',
})
export class ProjectDescriptionComponent implements OnInit {
  project: any = {};

  constructor(
    private managerService: ManagerService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = params.get('id');

      if (id) {
        this.managerService
          .getProjectById(parseInt(id, 10))
          .subscribe((res) => {
            this.project = res;
            let projectWorkhours = 0;

            for (let user of this.project.users) {
              let workHours = 0;
              for (let task of user.tasks) {
                workHours += task.hours;
              }
              user.workHours = workHours;
              projectWorkhours += workHours;
            }
            this.project.workHours = projectWorkhours;

            console.log(res);
          });
      }
    });
  }
}
