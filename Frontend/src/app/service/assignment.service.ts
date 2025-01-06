import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {

  private apiUrl = 'http://localhost:8080/EmployeeManagement/';

  constructor(private http: HttpClient) { }

  addAssignment(AssignmentData: any): Observable<any> {
      return this.http.post(`${this.apiUrl}CreateEmployeeProject`, AssignmentData);
    }
    getAllAssignments(): Observable<any> {
      return this.http.get(`${this.apiUrl}GetAllEmployeeProjects`);
    }
  
    updateAssignment(empProjectId: number, AssignmentData: any): Observable<any> {
      return this.http.put(`${this.apiUrl}UpdateAssignment/${empProjectId}`, AssignmentData);
    }
  
    deleteAssinment(empProjectId: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}DeleteAssignment/${empProjectId}`);
    }

    getAssignmentCount() {
      return this.http.get<number>(`${this.apiUrl}AssignmentCount`);
    }
}
