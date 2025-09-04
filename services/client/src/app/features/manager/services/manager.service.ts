import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../auth/storage/user-storage.service';

@Injectable({
  providedIn: 'root',
})
export class ManagerService {
  constructor(
    private http: HttpClient,
    private userStorage: UserStorageService
  ) {}

  projectToUpdate: any;

  postProject(projectDto: any): Observable<any> {
    const userId = this.userStorage.getUserId();
    const token = this.userStorage.getToken();

    console.log(projectDto, token);
    return this.http.post(`/api/manager/project/${userId}`, projectDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  postTask(taskDto: any): Observable<any> {
    const userId = this.userStorage.getUserId();
    return this.http.post(`/api/employee/task/${userId}`, taskDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllProjects(): Observable<any> {
    return this.http.get(`/api/manager/projects/dashboard`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getProjectById(id: number): Observable<any> {
    return this.http.get(`/api/project/${id}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getUserDashboard(): Observable<any> {
    return this.http.get(`/api/employee/dashboard`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  deleteProject(projectId: number): Observable<any> {
    return this.http.delete(`/api/manager/project/${projectId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  updateProject(projectId: number, project: any): Observable<any> {
    return this.http.put(`/api/manager/project/${projectId}`, project, {
      headers: this.createAuthorizationHeader(),
    });
  }

  private createAuthorizationHeader(): HttpHeaders {
    const token = this.userStorage.getToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }
}
