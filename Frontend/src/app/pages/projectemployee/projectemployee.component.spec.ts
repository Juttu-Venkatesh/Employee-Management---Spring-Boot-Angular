import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectemployeeComponent } from './projectemployee.component';

describe('ProjectemployeeComponent', () => {
  let component: ProjectemployeeComponent;
  let fixture: ComponentFixture<ProjectemployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectemployeeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectemployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
