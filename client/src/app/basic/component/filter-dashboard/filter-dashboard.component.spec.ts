import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterDashboardComponent } from './filter-dashboard.component';

describe('FilterDashboardComponent', () => {
  let component: FilterDashboardComponent;
  let fixture: ComponentFixture<FilterDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FilterDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FilterDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
