import { Component, inject, OnInit, ViewChild } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AddEmployeeComponent } from '../add-employee/add-employee.component';
import { MatIconModule } from '@angular/material/icon';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { EmployeeService } from '../../service/employee.service';


@Component({
  selector: 'app-employee',
  imports: [ 
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatSortModule
  ],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit{

  displayedColumns: string[] = ['employeeId', 'firstName', 'lastName', 'emailId', 'contactNo', 'gender', 'department', 'role', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  private employeeService = inject(EmployeeService);

  constructor(private _dialog: MatDialog) { }

  ngOnInit(): void {
    this.getEmployeeList();
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onAddEmployee(): void {
    this._dialog.open(AddEmployeeComponent);
  }

  getEmployeeList(): void {
    this.employeeService.getAllEmployees().subscribe({
      next: (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (err) => {
        console.error('Error fetching employees:', err);
      }
    });
  }

  openEditEmployee(data: any): void {
    const dialogRef = this._dialog.open(AddEmployeeComponent, {
      width: '500px',
      data: data,  // Pass the employee data to the dialog
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.getEmployeeList();  // Reload the employee list after successful submission
      }
    });
  }

  deleteEmployee(employeeId: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.employeeService.deleteEmployee(employeeId).subscribe({
        next: () => {
          alert('Employee deleted successfully.');
          this.getEmployeeList();
        },
        error: () => {
          alert('Failed to delete employee.');
        }
      });
    }
  }
}
