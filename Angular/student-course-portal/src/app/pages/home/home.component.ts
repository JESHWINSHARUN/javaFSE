import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h1>{{ portalName }}</h1>
    <p>Browse courses, track grades, and manage your enrollment all in one place.</p>
    <div class="stats">
      <span>Courses Available: {{ coursesCount }}</span>
      <span>Enrolled: 3</span>
      <span>GPA: 3.8</span>
    </div>
    <button [disabled]="!isPortalActive" (click)="onEnrollClick()">Enroll Now</button>
    <p *ngIf="message">{{ message }}</p>
    <input [(ngModel)]="searchTerm" placeholder="Search courses" />
    <p>Searching for: {{ searchTerm }}</p>
  `
})
export class HomeComponent implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  coursesCount = 0;

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {
    this.courseService.getCourses().subscribe(courses => this.coursesCount = courses.length);
    console.log('HomeComponent initialised — courses loaded');
  }

  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }
}
