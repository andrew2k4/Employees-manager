import { Component } from '@angular/core';
import { ManagerService } from '../../services/manager.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrls: ['./manager-dashboard.component.scss'],
  providers: [MessageService],
})
export class ManagerDashboardComponent {
  projects: any;
  isProjectDashboardActive: boolean = true;
  usersDashboard: any = []; // Initialisation correcte
  filteredUserDashboard: any = []; // Initialisation correcte

  selected: number = 0;

  constructor(
    private managerService: ManagerService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.getProjectDashboard();
    this.getUserDashboard();
    const toastMessage = history.state.toastMessage;
    console.log(toastMessage);
    if (toastMessage) {
      const severity = 'success';
      this.messageService.add({
        severity,
        summary: severity === 'success' ? 'Success' : 'Error',
        detail: toastMessage,
      });
    }
  }

  getUserDashboard() {
    this.managerService.getUserDashboard().subscribe((res) => {
      this.usersDashboard = res;
      // Recalcul des heures travaillées pour tous les utilisateurs
      this.recalculateWorkHours(this.usersDashboard);
      // Initialiser le tableau filtré
      this.filteredUserDashboard = this.usersDashboard.filter(
        (user) => user.workHours > 0
      );
    });
  }

  getProjectDashboard() {
    this.managerService.getAllProjects().subscribe((res) => {
      for (let project of res) {
        let projectWorkhours = 0;
        for (let user of project.users) {
          let workHours = 0;
          for (let task of user.tasks) {
            workHours += task.hours;
          }
          user.workHours = workHours;
          projectWorkhours += workHours;
        }
        project.workHours = projectWorkhours;
      }
      this.projects = res;
    });
  }

  // Méthode pour recalculer les heures de travail pour tous les utilisateurs
  recalculateWorkHours(users: any[]) {
    for (let user of users) {
      let workhours = 0;
      for (let task of user.tasks) {
        workhours += task.hours;
      }
      user.workHours = workhours;
    }
  }

  filterUsersWithWorkHours() {
    this.filteredUserDashboard = this.filteredUserDashboard.filter(
      (user) => user.workHours > 0
    );
  }

  onChangeFilter(event: number) {
    const today = new Date();

    if (event === 0) {
      this.filteredUserDashboard = [...this.usersDashboard]; // Cloner le tableau pour éviter les références partagées
    } else if (event === 1) {
      // Filtre 1 : Afficher uniquement les tâches de la semaine courante (du lundi au dimanche)
      const dayOfWeek = today.getDay(); // Dimanche = 0, Lundi = 1, ..., Samedi = 6
      const diff = (dayOfWeek === 0 ? -3 : 1) - dayOfWeek; // Ajustement pour obtenir lundi
      const startOfWeek = new Date(today);
      startOfWeek.setDate(today.getDate() + diff); // Obtenir lundi

      // Appliquer le filtre des tâches pour la semaine courante
      this.filteredUserDashboard = this.usersDashboard.map((user) => {
        const filteredTasks = user.tasks.filter(
          (task) => new Date(task.addedTime) >= startOfWeek
        );
        return { ...user, tasks: filteredTasks };
      });
    } else if (event === 2) {
      // Filtre 2 : Afficher uniquement les tâches du mois actuel
      const startOfMonth = new Date(today.getFullYear(), today.getMonth(), 1); // Début du mois
      this.filteredUserDashboard = this.usersDashboard.map((user) => {
        const filteredTasks = user.tasks.filter(
          (task) => new Date(task.addedTime) >= startOfMonth
        );
        return { ...user, tasks: filteredTasks };
      });
    }

    // Recalculer les heures de travail après application du filtre
    this.recalculateWorkHours(this.filteredUserDashboard);

    // Afficher uniquement les utilisateurs ayant des heures de travail > 0
    this.filteredUserDashboard = this.filteredUserDashboard.filter(
      (user) => user.workHours > 0
    );
  }
  changeDashboard(change) {
    this.isProjectDashboardActive = change;
  }
}
