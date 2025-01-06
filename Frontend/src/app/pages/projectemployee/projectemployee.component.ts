import { Component, OnInit, ViewChild } from '@angular/core';
import { MatButtonModule} from '@angular/material/button';
import { MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AddAssignmentComponent } from '../add-assignment/add-assignment.component';
import { AssignmentService } from '../../service/assignment.service';

@Component({
  selector: 'app-projectemployee',
  imports: [ MatButtonModule, MatFormFieldModule, MatToolbarModule,  MatIconModule, MatPaginatorModule,
    MatInputModule, MatTableModule, MatSortModule, MatDialogModule,],
  templateUrl: './projectemployee.component.html',
  styleUrl: './projectemployee.component.css'
})
export class ProjectemployeeComponent implements OnInit  {

  
    displayedColumns: string[] = ['empProjectId', 'projectId', 'employeeName', 'assignedDate', 'role', 'actions'];
    dataSource!: MatTableDataSource<any>;
  
    @ViewChild(MatPaginator) paginator!: MatPaginator;
    @ViewChild(MatSort) sort!: MatSort;

    constructor(private _dialog: MatDialog,
               private assigneeService: AssignmentService
    ) {

    }
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
    onAddAssignment() {
      this._dialog.open(AddAssignmentComponent);
    }

    getEmployeeList(): void {
      this.assigneeService.getAllAssignments().subscribe({
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
    openEditAssignment(data:any) {
      const dialogRef = this._dialog.open(AddAssignmentComponent, {
            width: '500px',
            data: data,  // Pass the employee data to the dialog
          });
      
          dialogRef.afterClosed().subscribe((result: boolean) => {
            if (result) {
              this.getEmployeeList();  // Reload the employee list after successful submission
            }
          });
    }

    deleteAssignment(empProjectId:any) {
      if (confirm('Are you sure you want to delete this employee?')) {
        this.assigneeService.deleteAssinment(empProjectId).subscribe({
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
