import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../auth/storage/user-storage.service';

const Basic_Url = '/api/';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  constructor(
    private http: HttpClient,
    private userStorage: UserStorageService // ✅ injection du service
  ) {}

  postProject(projectDto: any): Observable<any> {
    const userId = this.userStorage.getUserId();
    const token = this.userStorage.getToken();

    console.log(projectDto, token);
    return this.http.post(`${Basic_Url}employee/task/${userId}`, projectDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  postTask(taskDto: any): Observable<any> {
    const userId = this.userStorage.getUserId();
    return this.http.post(`${Basic_Url}employee/task/${userId}`, taskDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllProjects(): Observable<any> {
    return this.http.get(`${Basic_Url}manager/projects`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  private createAuthorizationHeader(): HttpHeaders {
    const token = this.userStorage.getToken(); // ✅ via instance
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }
}
