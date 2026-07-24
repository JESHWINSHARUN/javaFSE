import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Course } from '../../models/course.model';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';
import { HighlightDirective } from '../../directives/highlight.directive';
import { EnrollmentService } from '../../services/enrollment.service';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CommonModule, CreditLabelPipe, HighlightDirective],
  template: `
    <div class="card" appHighlight [ngClass]="cardClasses" [ngStyle]="{ borderLeftColor: borderColor }">
      <h3>{{ course.name }}</h3>
      <p>{{ course.code }}</p>
      <p>{{ course.credits | creditLabel }}</p>
      <span [ngSwitch]="course.gradeStatus">
        <span *ngSwitchCase="'passed'" class="badge-green">Passed</span>
        <span *ngSwitchCase="'failed'" class="badge-red">Failed</span>
        <span *ngSwitchDefault class="badge-grey">Pending</span>
      </span>
      <button (click)="toggleExpand()">Show Details</button>
      <div *ngIf="isExpanded">
        <p>Course ID: {{ course.id }}</p>
      </div>
      <button (click)="onEnrollClick()">{{ enrolled ? 'Unenroll' : 'Enroll' }}</button>
    </div>
  `,
  styles: [`
    .card { border: 1px solid #ccc; border-left-width: 4px; padding: 1rem; margin: 0.5rem 0; }
    .card--enrolled { background: #ecfdf5; }
    .card--full { opacity: 0.85; }
    .expanded { min-height: 150px; }
  `]
})
export class CourseCardComponent implements OnChanges {
  @Input() course!: Course;
  @Output() enrollRequested = new EventEmitter<number>();

  isExpanded = false;

  constructor(private enrollmentService: EnrollmentService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      console.log('previous:', changes['course'].previousValue, 'current:', changes['course'].currentValue);
    }
  }

  get enrolled(): boolean {
    return this.enrollmentService.isEnrolled(this.course.id);
  }

  get cardClasses() {
    return {
      'card--enrolled': this.enrolled,
      'card--full': this.course.credits >= 4,
      'expanded': this.isExpanded
    };
  }

  get borderColor(): string {
    switch (this.course.gradeStatus) {
      case 'passed': return 'green';
      case 'failed': return 'red';
      default: return 'grey';
    }
  }

  toggleExpand(): void {
    this.isExpanded = !this.isExpanded;
  }

  onEnrollClick(): void {
    if (this.enrolled) {
      this.enrollmentService.unenroll(this.course.id);
    } else {
      this.enrollmentService.enroll(this.course.id);
    }
    this.enrollRequested.emit(this.course.id);
  }
}
