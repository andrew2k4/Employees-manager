import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';
const Basic_Url = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  constructor(private http: HttpClient) {}

  postProject(projectDto: any): Observable<any> {
    const userId = UserStorageService.getUserId();

    console.log(projectDto, UserStorageService.getToken());
    return this.http.post(
      `${Basic_Url}api/manager/project/${userId}`,
      projectDto,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }

  postTask(taskDto: any): Observable<any> {
    const userId = UserStorageService.getUserId();
    return this.http.post(`${Basic_Url}api/employee/task/${userId}`, taskDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllProjects(): Observable<any> {
    return this.http.get(`${Basic_Url}api/manager/projects`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization',
      'Bearer ' + UserStorageService.getToken()
    );
  }
}
