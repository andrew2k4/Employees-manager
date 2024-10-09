import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupManagerComponent } from './signup-manager.component';

describe('SignupManagerComponent', () => {
  let component: SignupManagerComponent;
  let fixture: ComponentFixture<SignupManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SignupManagerComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SignupManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
