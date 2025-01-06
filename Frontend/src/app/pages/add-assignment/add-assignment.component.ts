import { Component, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EmployeeService } from '../../service/employee.service';
import { ProjectService } from '../../service/project.service';
import { AssignmentService } from '../../service/assignment.service';

@Component({
  selector: 'app-add-assignment',
  imports: [ MatButtonModule, ReactiveFormsModule, MatInputModule, MatNativeDateModule, MatRadioModule,
    CommonModule, FormsModule, MatSelectModule, MatDialogModule
  ],
  templateUrl: './add-assignment.component.html',
  styleUrl: './add-assignment.component.css'
})
export class AddAssignmentComponent implements OnInit {

  assignForm!: FormGroup;
  employees: any[] = [];
  projects: any[] = [];
  roles: any[] = ["Software Developer", "Frontend Developer", "Backend Developer", "Full Stack Developer", "DevOps Engineer", "Data Scientist", "QA Engineer", "Product Manager", "UI/UX Designer", "Cybersecurity Specialist"];
  isEditMode: any;
  constructor(
    private fb: FormBuilder,
    public _dialogRef: MatDialogRef<AddAssignmentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private employeeService: EmployeeService,
    private projectService: ProjectService,
    private assignmentService: AssignmentService
  ) {}


  ngOnInit(): void {
    this.fetchEmployees();
    this. fetchProjects();

    this.isEditMode = !!this.data?.projectId;

    this.assignForm = this.fb.group({
      projectId: [this.isEditMode ? this.data.projectId : '', Validators.required],
      employeeName: [this.isEditMode ? this.data.employeeName : '', Validators.required],
      assignedDate: [this.isEditMode ? this.data.assignedDate : '', Validators.required],
      role:[this.isEditMode ? this.data.role : '', Validators.required]

    });
  }

  fetchEmployees(): void {
    this.employeeService.getAllEmployees().subscribe(
      (response) => {
        this.employees = response;
      },
      (error) => {
        console.error('Error fetching employees:', error);
      }
    );
  }

  fetchProjects(): void {
    this.projectService.getAllProject().subscribe(
      (response) => {
        this.projects = response;
      },
      (error) => {
        console.error('Error fetching Projects:', error);
      }
    );
  }

  onFormSubmit() {
    if (this.assignForm.valid) {
      if (this.isEditMode) {
        this.assignmentService.updateAssignment(this.data.empProjectId, this.assignForm.value).subscribe({
          next: () => {
            alert('Assignment updated successfully!');
            this._dialogRef.close(true);
          },
          error: () => {
            alert('Failed to update Assignment');
            this._dialogRef.close();
          },
        });
      } else {
        this.assignmentService.addAssignment(this.assignForm.value).subscribe({
          next: () => {
            alert('Assignment created successfully!');
            this._dialogRef.close(true);
          },
          error: () => {
            alert('Failed to create Assignment.');
            this._dialogRef.close();
          },
        });
      }
    } else {
      alert('Please fill out all required fields.');
    }
  }
}
