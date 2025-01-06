import { Component, inject } from '@angular/core';
import { RouterLink, Router, RouterOutlet } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
@Component({
  selector: 'app-layout',
  imports: [ RouterLink, MatSidenavModule, MatToolbarModule, MatIconModule, MatListModule,
    RouterOutlet,MatButtonModule
  ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {

  
  router = inject(Router);
  logoff() {
    this.router.navigateByUrl('home'); 
  }
}
