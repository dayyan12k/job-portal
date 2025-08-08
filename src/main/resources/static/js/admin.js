const baseURL = "http://localhost:8080";
console.log("JavaScript maki chut teri")
async function doLogout() {
  Swal.fire({
    title: "Do you want to Log Out?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Delete",
  }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
      const url = `${baseURL}/do-logout`
      window.location.replace(url);
    }
  });
}

//view contacts function
function showDetails(id) {
  document.getElementById("details-" + id).classList.remove("hidden");
}

function hideDetails(id) {
  document.getElementById("details-" + id).classList.add("hidden");
}


// function showDetails(jobId) {
//     const modal = document.getElementById('details-' + jobId);
//     if (modal) {
//         modal.classList.remove('hidden');
//         document.body.style.overflow = 'hidden';
//     }
// }

// function hideDetails(jobId) {
//     const modal = document.getElementById('details-' + jobId);
//     if (modal) {
//         modal.classList.add('hidden');
//         document.body.style.overflow = 'auto';
//     }
// }

// Close modal when clicking outside
document.addEventListener('click', function (event) {
  if (event.target.classList.contains('bg-black') && event.target.classList.contains('bg-opacity-50')) {
    event.target.classList.add('hidden');
    document.body.style.overflow = 'auto';
  }
});

// Close modal with Escape key
document.addEventListener('keydown', function (event) {
  if (event.key === 'Escape') {
    const activeModals = document.querySelectorAll('.fixed:not(.hidden)');
    activeModals.forEach(modal => {
      if (modal.id.startsWith('details-')) {
        modal.classList.add('hidden');
        document.body.style.overflow = 'auto';
      }
    });
  }
});

// Save/Unsave job functionality
function toggleSaveJob(button) {
  const jobId = button.getAttribute('data-job-id');
  const span = button.querySelector('span');
  const svg = button.querySelector('svg');
  const isSaved = span.textContent === 'Saved';

  // Disable button during request
  button.disabled = true;
  button.style.opacity = '0.5';

  const url = isSaved ? `/user/unsave-job/${jobId}` : `/user/save-job/${jobId}`;

  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
  })
    .then(response => response.text())
    .then(data => {
      if (data === 'saved') {
        span.textContent = 'Saved';
        button.classList.remove('text-gray-600', 'hover:text-gray-800');
        button.classList.add('text-blue-600', 'hover:text-blue-800');
        svg.classList.add('fill-current');
      } else if (data === 'unsaved') {
        span.textContent = 'Save';
        button.classList.remove('text-blue-600', 'hover:text-blue-800');
        button.classList.add('text-gray-600', 'hover:text-gray-800');
        svg.classList.remove('fill-current');
      } else {
        alert('Please login to save jobs');
      }
    })
    .catch(error => {
      console.error('Error:', error);
      alert('An error occurred. Please try again.');
    })
    .finally(() => {
      // Re-enable button
      button.disabled = false;
      button.style.opacity = '1';
    });
}
