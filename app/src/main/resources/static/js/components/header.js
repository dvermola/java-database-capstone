/**
 * Header component for Hospital CMS
 * Renders the header section based on user role and session status
 */
function renderHeader() {
  let headerDiv = document.getElementById("header");
  
  // Check if current page is root page
  if (window.location.pathname.endsWith("/")) {
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");

    headerDiv.innerHTML = `
    <header class="header">
      <div class="logo-section">
        <img src="/assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
        <span class="logo-title">Hospital CMS</span>
      </div>
    </header>`;

    return;
  }

  // Get user role and token from localStorage
  let role = localStorage.getItem("userRole");
  let token = localStorage.getItem("token");
  console.log(role, token);
  
  // Initialize header content with logo section
  let headerContent = `
  <header class="header">
    <div class="logo-section">
      <img src="/assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
      <span class="logo-title">Hospital CMS</span>
    </div>
    <nav>`;
  
  // Handle session expiry or invalid login
  if ((role === "loggedPatient" || role === "admin" || role === "doctor") && !token) {
    localStorage.removeItem("userRole");
    alert("Session expired or invalid login. Please log in again.");
    window.location.href = "/";
    return;
  }
  
  // Add role-specific header content
  if (role === "admin") {
    headerContent += `
      <button id="addDocBtn" class="adminBtn" onclick="openModal('addDoctor')">Add Doctor</button>
      <a href="#" onclick="logout()">Logout</a>`;
  } else if (role === "doctor") {
    headerContent += `
      <button class="adminBtn" onclick="selectRole('doctor')">Home</button>
      <a href="#" onclick="logout()">Logout</a>`;
  } else if (role === "patient") {
    headerContent += `
      <button id="patientLogin" class="adminBtn">Login</button>
      <button id="patientSignup" class="adminBtn">Sign Up</button>`;
  } else if (role === "loggedPatient") {
    headerContent += `
      <button id="home" class="adminBtn" onclick="window.location.href='/pages/loggedPatientDashboard.html'">Home</button>
      <button id="patientAppointments" class="adminBtn" onclick="window.location.href='/pages/patientAppointments.html'">Appointments</button>
      <a href="#" onclick="logoutPatient()">Logout</a>`;
  }
  
  // Close the header section
  headerContent += `
    </nav>
  </header>`;
  
  // Render the header content
  headerDiv.innerHTML = headerContent;
  
  // Attach event listeners to header buttons
  attachHeaderButtonListeners();
}

/**
 * Attaches event listeners to header buttons
 */
function attachHeaderButtonListeners() {
  const patientLoginBtn = document.getElementById("patientLogin");
  const patientSignupBtn = document.getElementById("patientSignup");
  
  if (patientLoginBtn) {
    patientLoginBtn.addEventListener("click", () => {
      openModal("patientLogin");
    });
  }
  
  if (patientSignupBtn) {
    patientSignupBtn.addEventListener("click", () => {
      openModal("patientSignup");
    });
  }
}

/**
 * Logs out the user by removing session data and redirecting to root page
 */
function logout() {
  localStorage.removeItem("userRole");
  localStorage.removeItem("token");
  window.location.href = "/";
}

/**
 * Logs out a patient user and redirects to patient dashboard
 */
function logoutPatient() {
  localStorage.removeItem("token");
  localStorage.removeItem("userRole");
  window.location.href = "/pages/patientDashboard.html";
}

// Initialize header rendering when page loads
renderHeader();

