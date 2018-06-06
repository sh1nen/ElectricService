import { Component, OnInit } from '@angular/core';
import { LoginService } from './../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  
  loggedIn: boolean;
  
    constructor(private loginService: LoginService, private router : Router) {
      if(localStorage.getItem('PortalUserLoggedIn') == '') {
        this.loggedIn = false;
      } else {
        this.loggedIn = true;
      }
    }
  
    logout(){
      this.loginService.logout().subscribe(
        res => {
          localStorage.setItem('PortalUserLoggedIn', '');
        },
        err => console.log(err)
        );
      location.reload();
      this.router.navigate(['/login']);
    }
  
    ngOnInit() {
    }
}