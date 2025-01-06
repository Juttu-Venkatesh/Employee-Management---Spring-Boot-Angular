import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-login',
  imports: [ ReactiveFormsModule, MatButtonModule, FormsModule, CommonModule,
    MatFormFieldModule,MatInputModule, MatCardModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm!: FormGroup;

  http = inject(HttpClient);
  constructor(private _fb : FormBuilder,
    private router: Router,
  ){
    this.loginForm = this._fb.group({
      emailId: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }
  onLogin() {
    this.router.navigate(['/layout/dashboard']);
  }

}
