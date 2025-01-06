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

@Component({
  selector: 'app-add-project',
  standalone: true,
  imports: [
    MatButtonModule,
    MatInputModule,
    MatNativeDateModule,
    MatRadioModule,
    MatSelectModule,
    MatDatepickerModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    CommonModule,
    FormsModule,
    MatDialogModule,
  ],
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css'],
})
export class AddProjectComponent implements OnInit {
  projectForm!: FormGroup;
  employees: any[] = [];  // Ensure you have employees populated
  leadByEmployee: any[] = [];
  department: string[] = [
    'Software Development',
    'Data Science',
    'Digital Marketing',
    'AI/ML Engineer',
  ];
  isEditMode = false;

  constructor(
    private projectService: ProjectService,
    private employeeService: EmployeeService,
    private _dialogRef: MatDialogRef<AddProjectComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _fb: FormBuilder,
  ) {}

  ngOnInit(): void {
    // Fetch employees and populate the dropdown
    this.fetchEmployees();

    // Check if in edit mode
    this.isEditMode = !!this.data?.projectId;

    // Initialize the form
    this.projectForm = this._fb.group({
      projectName: [this.isEditMode ? this.data.projectName : '', Validators.required],
      clientName: [this.isEditMode ? this.data.clientName : '', Validators.required],
      emailId: [this.isEditMode ? this.data.emailId : '', [Validators.required, Validators.email]],
      contactNo: [this.isEditMode ? this.data.contactNo : '', Validators.required],
      startDate: [this.isEditMode ? this.data.startDate : '', Validators.required],
      contactPerson: [this.isEditMode ? this.data.contactPerson : '', Validators.required],
      leadByEmployee: [this.isEditMode ? this.data.leadByEmployee : '', Validators.required],
    });
  }

  // Fetch employees from the backend
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

  onFormSubmit(): void {
    if (this.projectForm.valid) {
      if (this.isEditMode) {
        this.projectService.updateProject(this.data.projectId, this.projectForm.value).subscribe({
          next: () => {
            alert('Project updated successfully!');
            this._dialogRef.close(true);
          },
          error: () => {
            alert('Failed to update project.');
            this._dialogRef.close();
          },
        });
      } else {
        this.projectService.addProject(this.projectForm.value).subscribe({
          next: () => {
            alert('Project created successfully!');
            this._dialogRef.close(true);
          },
          error: () => {
            alert('Failed to create project.');
            this._dialogRef.close();
          },
        });
      }
    } else {
      alert('Please fill out all required fields.');
    }
  }
}
