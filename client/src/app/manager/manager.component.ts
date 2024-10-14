import { Component, OnInit } from '@angular/core';
import { ManagerService } from './services/manager.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss'], // Notez le "styleUrls" avec un 's' à la fin
})
export class ManagerComponent implements OnInit {
  ngOnInit() {}
}
