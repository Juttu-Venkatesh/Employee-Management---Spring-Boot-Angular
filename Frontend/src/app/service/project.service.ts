import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private apiUrl = 'http://localhost:8080/EmployeeManagement/'
  constructor(private http: HttpClient) { }

  addProject(projectData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}CreateProject`, projectData);
  }
  getAllProject(): Observable<any> {
      return this.http.get(`${this.apiUrl}GetAllProjects`);
    }

    updateProject(projectId: number, projectData: any): Observable<any> {
      return this.http.put(`${this.apiUrl}UpdateProject/${projectId}`, projectData);
    }
  
    deleteProject(projectId: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}DeleteProject/${projectId}`);
    }

    getProjectCount() {
      return this.http.get<number>(`${this.apiUrl}ProductCount`);
    }
}
