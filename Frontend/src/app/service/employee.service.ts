import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl = 'http://localhost:8080/EmployeeManagement/'

  constructor(private http: HttpClient) { }

  addEmployee(employeeData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}CreateEmployee`, employeeData);
  }
  getAllEmployees(): Observable<any> {
    return this.http.get(`${this.apiUrl}GetAllEmployees`);
  }

  updateEmployee(employeeId: number, employeeData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}UpdateEmployee/${employeeId}`, employeeData);
  }

  deleteEmployee(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}DeleteEmployee/${employeeId}`);
  }

  getEmployeeCount() {
    return this.http.get<number>(`${this.apiUrl}EmployeeCount`);
  }
  
}
