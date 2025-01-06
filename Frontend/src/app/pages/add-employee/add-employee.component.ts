import { Component, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { FormBuilder, FormGroup, ReactiveFormsModule,Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EmployeeService } from '../../service/employee.service';

@Component({
  selector: 'app-add-employee',
  imports: [
    MatButtonModule, MatInputModule, MatSelectModule, MatFormFieldModule, CommonModule,
    ReactiveFormsModule,MatDialogModule
  ],
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  empForm!: FormGroup;
  gender: string[] = ['Male', 'Female', 'Other'];
  department: string[] = ['Software Development', 'Data Science', 'Digital Marketing', 'AI/ML Engineer'];
  isEditMode: boolean = false;  // Flag to check if we're editing

  constructor(
    private employeeService: EmployeeService,
    private _dialogRef: MatDialogRef<AddEmployeeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.isEditMode = this.data && this.data.employeeId ? true : false;  // Check if we are in edit mode

    this.empForm = this._fb.group({
      firstName: [this.isEditMode ? this.data.firstName : '', Validators.required],
      lastName: [this.isEditMode ? this.data.lastName : '', Validators.required],
      emailId: [this.isEditMode ? this.data.emailId : '', [Validators.required, Validators.email]],
      contactNo: [this.isEditMode ? this.data.contactNo : '', Validators.required],
      password: [this.isEditMode ? this.data.password : '', Validators.required],
      gender: [this.isEditMode ? this.data.gender : '', Validators.required],
      department: [this.isEditMode ? this.data.department : '', Validators.required],
      role: [this.isEditMode ? this.data.role : '', Validators.required],
    });
  }

  onFormSubmit(): void {
    if (this.empForm.valid) {
      if (this.isEditMode) {
        this.employeeService.updateEmployee(this.data.employeeId, this.empForm.value).subscribe({
          next: (response) => {
            alert('Employee updated successfully!');
            this._dialogRef.close(true); 
          },
          error: () => {
            alert('Failed to update employee.');
            this._dialogRef.close();
          }
        });
      } else {
        this.employeeService.addEmployee(this.empForm.value).subscribe({
          next: (response) => {
            alert('Employee created successfully!');
            this._dialogRef.close(true);  
          },
          error: () => {
            alert('Failed to create employee.');
            this._dialogRef.close();
          }
        });
      }
    } else {
      alert('Please fill out all required fields.');
    }
  }
}
