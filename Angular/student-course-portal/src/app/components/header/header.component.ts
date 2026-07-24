import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  template: `
    <nav>
      <span class="brand">Student Course Portal</span>
      <a routerLink="/">Home</a>
      <a routerLink="/courses">Courses</a>
      <a routerLink="/profile">Profile</a>
    </nav>
  `,
  styles: [`
    nav { display: flex; gap: 1rem; padding: 1rem; background: #1e293b; color: #fff; }
    a { color: #fff; text-decoration: none; }
    .brand { font-weight: bold; margin-right: 2rem; }
  `]
})
export class HeaderComponent {}
