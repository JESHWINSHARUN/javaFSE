import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { CourseCardComponent } from './course-card.component';

describe('CourseCardComponent', () => {
  let component: CourseCardComponent;
  let fixture: ComponentFixture<CourseCardComponent>;

  const mockCourse = { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' as const };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseCardComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCardComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render course name', () => {
    component.course = mockCourse;
    fixture.detectChanges();
    const h3 = fixture.debugElement.query(By.css('h3')).nativeElement;
    expect(h3.textContent).toContain('Data Structures');
  });

  it('should emit enrollRequested on enroll button click', () => {
    component.course = mockCourse;
    fixture.detectChanges();
    spyOn(component.enrollRequested, 'emit');
    const buttons = fixture.debugElement.queryAll(By.css('button'));
    buttons[1].nativeElement.click();
    fixture.detectChanges();
    expect(component.enrollRequested.emit).toHaveBeenCalledWith(1);
  });

  it('should log previous and current values on ngOnChanges', () => {
    spyOn(console, 'log');
    component.course = mockCourse;
    component.ngOnChanges({
      course: { previousValue: null, currentValue: mockCourse, firstChange: true, isFirstChange: () => true }
    });
    expect(console.log).toHaveBeenCalled();
  });

  it('should toggle isExpanded on Show Details click', () => {
    component.course = mockCourse;
    fixture.detectChanges();
    const showDetailsBtn = fixture.debugElement.queryAll(By.css('button'))[0];
    expect(component.isExpanded).toBeFalse();
    showDetailsBtn.nativeElement.click();
    expect(component.isExpanded).toBeTrue();
  });
});
