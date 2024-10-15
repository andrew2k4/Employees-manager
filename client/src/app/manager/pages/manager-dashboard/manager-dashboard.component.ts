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

      // Vérification si "projects" est un tableau valide
      if (Array.isArray(this.projects)) {
        for (let i = 0; i < this.projects.length; i++) {
          const project = {
            projectName: this.projects[i]?.projectName || 'Unknown Project', // Vérification de projectName
            date: new Date(this.projects[i]?.updatedAt).toLocaleDateString(
              'fr-FR'
            ),
            clientName: this.projects[i]?.clientName,
            users: [],
          };

          // Vérifier si "users" existe et est un tableau
          if (Array.isArray(this.projects[i]?.users)) {
            for (let j = 0; j < this.projects[i].users.length; j++) {
              let taskUser: any[] = [];
              let userId = this.projects[i].users[j].id;
              let hoursTravailler = 0; // Initialisation des heures pour chaque utilisateur

              // Vérifier si "tasks" existe et est un tableau
              if (Array.isArray(this.projects[i]?.tasks)) {
                for (let k = 0; k < this.projects[i].tasks.length; k++) {
                  if (this.projects[i].tasks[k].userId === userId) {
                    taskUser.push(this.projects[i].tasks[k]); // Ajoute la tâche à l'utilisateur
                    hoursTravailler += this.projects[i].tasks[k].hours; // Ajoute les heures travaillées
                  }
                }
              }

              // Ajouter l'utilisateur et ses tâches au projet
              project.users.push({
                user: this.projects[i].users[j], // Utilisateur actuel
                tasks: taskUser, // Liste des tâches associées à cet utilisateur
                hoursTravailler: hoursTravailler, // Total des heures travaillées
              });
            }
          }

          // Ajouter le projet avec les utilisateurs et leurs tâches à mappedProjects
          this.mappedProjects.push(project);
        }
      } else {
        console.error("La réponse des projets n'est pas un tableau valide");
      }

      console.log(this.mappedProjects); // Affiche le résultat mappé dans la console
    });
  }
}
