import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { EmployeeService } from '../../service/employee.service';
import { ProjectService } from '../../service/project.service';
import { AssignmentService } from '../../service/assignment.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
  ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  totalEmployeesCount: number = 0;
  totalProjectsCount: number = 0;
  totalAssignmentsCount: number = 0;

  constructor(
    private employeeService: EmployeeService,
    private projectService: ProjectService,
    private assignmentService: AssignmentService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadEmployeeCount();
    this.loadProjectCount();
    this.loadAssignmentCount();
  }

  loadEmployeeCount(): void {
    this.employeeService.getEmployeeCount().subscribe(
      (count: number) => {
        debugger;
        console.log(count);
        this.totalEmployeesCount = count;
        this.cdr.detectChanges();
      },
      (error) => {
        console.error('Error fetching employee count', error);
      }
    );
  }
  
  loadProjectCount(): void {
    this.projectService.getProjectCount().subscribe(
      (count: number) => {
        debugger;
        console.log(count);
        this.totalProjectsCount = count;
        this.cdr.detectChanges();
      },
      (error) => {
        console.error('Error fetching project count', error);
      }
    );
  }
  
  loadAssignmentCount(): void {
    this.assignmentService.getAssignmentCount().subscribe(
      (count: number) => {
        debugger;
        console.log(count);
        this.totalAssignmentsCount = count;
        this.cdr.detectChanges();
      },
      (error) => {
        console.error('Error fetching assignment count', error);
      }
    );
  }
  
}
