/**
 * Campus Placement and Internship Portal - Core Frontend Application
 * Antigravity Motion & Reactive UI Controller
 */

// Theme Engine (Dark & Light Mode Persistence)
function initTheme() {
    const urlParams = new URLSearchParams(window.location.search);
    const urlTheme = urlParams.get('theme');
    const savedTheme = urlTheme || localStorage.getItem('portal_theme') || 'dark';
    setTheme(savedTheme);
}

function setTheme(theme) {
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('portal_theme', theme);

    // Update all theme toggle buttons across the UI
    document.querySelectorAll('.theme-toggle-btn').forEach(btn => {
        if (theme === 'light') {
            btn.innerHTML = '<span class="theme-toggle-icon">🌙</span><span>Dark Mode</span>';
            btn.setAttribute('title', 'Switch to Deep Space Dark Theme');
        } else {
            btn.innerHTML = '<span class="theme-toggle-icon">☀️</span><span>Light Mode</span>';
            btn.setAttribute('title', 'Switch to Frost Alabaster Light Theme');
        }
    });

    window.dispatchEvent(new CustomEvent('themeChanged', { detail: { theme } }));
}

function toggleTheme() {
    const current = document.documentElement.getAttribute('data-theme') || 'dark';
    const next = current === 'light' ? 'dark' : 'light';
    setTheme(next);
}

// Global Constellation Particle System
function initConstellation() {
    const canvas = document.getElementById('canvas-constellation');
    if (!canvas) return;
    const ctx = canvas.getContext('2d');

    let width = canvas.width = window.innerWidth;
    let height = canvas.height = window.innerHeight;

    window.addEventListener('resize', () => {
        width = canvas.width = window.innerWidth;
        height = canvas.height = window.innerHeight;
    });

    const particles = [];
    const count = Math.min(width > 768 ? 60 : 30, 80);

    for (let i = 0; i < count; i++) {
        particles.push({
            x: Math.random() * width,
            y: Math.random() * height,
            vx: (Math.random() - 0.5) * 0.6,
            vy: (Math.random() - 0.5) * 0.6,
            radius: Math.random() * 2 + 1
        });
    }

    function animate() {
        ctx.clearRect(0, 0, width, height);
        const isLight = document.documentElement.getAttribute('data-theme') === 'light';

        for (let i = 0; i < particles.length; i++) {
            const p = particles[i];
            p.x += p.vx;
            p.y += p.vy;

            if (p.x < 0 || p.x > width) p.vx *= -1;
            if (p.y < 0 || p.y > height) p.vy *= -1;

            ctx.beginPath();
            ctx.arc(p.x, p.y, p.radius, 0, Math.PI * 2);
            ctx.fillStyle = isLight ? 'rgba(79, 70, 229, 0.45)' : 'rgba(0, 240, 255, 0.4)';
            ctx.fill();

            // Connect nearby nodes
            for (let j = i + 1; j < particles.length; j++) {
                const p2 = particles[j];
                const dx = p.x - p2.x;
                const dy = p.y - p2.y;
                const dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < 130) {
                    ctx.beginPath();
                    ctx.moveTo(p.x, p.y);
                    ctx.lineTo(p2.x, p2.y);
                    ctx.strokeStyle = isLight ? 
                        `rgba(79, 70, 229, ${0.12 * (1 - dist / 130)})` : 
                        `rgba(99, 102, 241, ${0.15 * (1 - dist / 130)})`;
                    ctx.lineWidth = 0.8;
                    ctx.stroke();
                }
            }
        }
        requestAnimationFrame(animate);
    }
    animate();
}

// Toast Alert Engine
function showToast(message, type = 'info') {
    let container = document.querySelector('.toast-container');
    if (!container) {
        container = document.createElement('div');
        container.className = 'toast-container';
        document.body.appendChild(container);
    }

    const toast = document.createElement('div');
    toast.className = 'toast';
    const icon = type === 'success' ? '✅' : type === 'error' ? '❌' : 'ℹ️';
    toast.innerHTML = `<span>${icon}</span><span>${message}</span>`;
    container.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateX(100%)';
        toast.style.transition = 'all 0.3s ease';
        setTimeout(() => toast.remove(), 300);
    }, 4000);
}

// Modal Engine
function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = 'flex';
    }
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = 'none';
    }
}

// Auth API Client
const Auth = {
    async getCurrentUser() {
        try {
            const res = await fetch('/api/auth/me');
            const data = await res.json();
            return data.authenticated ? data.user : null;
        } catch {
            return null;
        }
    },

    async logout() {
        try {
            await fetch('/api/auth/logout', { method: 'POST' });
            window.location.href = '/login.html';
        } catch {
            window.location.href = '/login.html';
        }
    },

    async requireAuth(allowedRole) {
        const user = await this.getCurrentUser();
        if (!user) {
            window.location.href = '/login.html';
            return null;
        }
        if (allowedRole && user.role !== allowedRole) {
            alert(`Unauthorized access! This portal requires ${allowedRole} role.`);
            if (user.role === 'STUDENT') window.location.href = '/student-dashboard.html';
            else if (user.role === 'RECRUITER') window.location.href = '/recruiter-dashboard.html';
            else if (user.role === 'ADMIN') window.location.href = '/admin-dashboard.html';
            return null;
        }
        // Update user display names
        document.querySelectorAll('.current-user-name').forEach(el => el.textContent = user.name);
        document.querySelectorAll('.current-user-role').forEach(el => el.textContent = user.role);
        return user;
    }
};

// Canvas Chart Renderer (Pure Vanilla JavaScript, Zero External Dependency)
const ChartRenderer = {
    isLightMode() {
        return document.documentElement.getAttribute('data-theme') === 'light';
    },

    renderBarChart(canvasId, labels, dataValues, primaryColor) {
        const canvas = document.getElementById(canvasId);
        if (!canvas) return;
        const ctx = canvas.getContext('2d');
        const w = canvas.width;
        const h = canvas.height;
        const light = this.isLightMode();
        const primary = primaryColor || (light ? '#4f46e5' : '#00f0ff');

        ctx.clearRect(0, 0, w, h);

        const padding = 40;
        const maxVal = Math.max(...dataValues, 10);
        const barWidth = (w - padding * 2) / labels.length * 0.55;
        const step = (w - padding * 2) / labels.length;

        // Draw Axes
        ctx.strokeStyle = light ? 'rgba(0, 0, 0, 0.08)' : 'rgba(255, 255, 255, 0.1)';
        ctx.lineWidth = 1;
        ctx.beginPath();
        ctx.moveTo(padding, h - padding);
        ctx.lineTo(w - padding, h - padding);
        ctx.stroke();

        for (let i = 0; i < labels.length; i++) {
            const x = padding + i * step + step * 0.22;
            const barHeight = (dataValues[i] / maxVal) * (h - padding * 2);
            const y = h - padding - barHeight;

            // Bar Gradient
            const grad = ctx.createLinearGradient(x, y, x, h - padding);
            grad.addColorStop(0, primary);
            grad.addColorStop(1, light ? 'rgba(79, 70, 229, 0.15)' : 'rgba(99, 102, 241, 0.2)');

            ctx.fillStyle = grad;
            ctx.beginPath();
            ctx.roundRect(x, y, barWidth, barHeight, [4, 4, 0, 0]);
            ctx.fill();

            // Value text
            ctx.fillStyle = light ? '#0f172a' : '#f8fafc';
            ctx.font = 'bold 11px Inter, sans-serif';
            ctx.textAlign = 'center';
            ctx.fillText(dataValues[i], x + barWidth / 2, y - 6);

            // Label text
            ctx.fillStyle = light ? '#475569' : '#94a3b8';
            ctx.font = '10px Inter, sans-serif';
            const shortLabel = labels[i].length > 12 ? labels[i].substring(0, 10) + '..' : labels[i];
            ctx.fillText(shortLabel, x + barWidth / 2, h - padding + 16);
        }
    },

    renderDonutChart(canvasId, labels, dataValues, colors) {
        const canvas = document.getElementById(canvasId);
        if (!canvas) return;
        const ctx = canvas.getContext('2d');
        const w = canvas.width;
        const h = canvas.height;
        const centerX = w / 2;
        const centerY = h / 2;
        const outerRadius = Math.min(centerX, centerY) - 20;
        const innerRadius = outerRadius * 0.6;
        const light = this.isLightMode();

        ctx.clearRect(0, 0, w, h);

        const total = dataValues.reduce((a, b) => a + b, 0);
        if (total === 0) return;

        let startAngle = -Math.PI / 2;

        for (let i = 0; i < dataValues.length; i++) {
            const sliceAngle = (dataValues[i] / total) * (Math.PI * 2);
            const endAngle = startAngle + sliceAngle;

            ctx.beginPath();
            ctx.arc(centerX, centerY, outerRadius, startAngle, endAngle);
            ctx.arc(centerX, centerY, innerRadius, endAngle, startAngle, true);
            ctx.closePath();

            ctx.fillStyle = colors[i % colors.length];
            ctx.fill();

            startAngle = endAngle;
        }

        // Center Total Text
        ctx.fillStyle = light ? '#0f172a' : '#f8fafc';
        ctx.font = 'bold 20px Space Grotesk, sans-serif';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(total, centerX, centerY - 8);

        ctx.fillStyle = light ? '#475569' : '#94a3b8';
        ctx.font = '10px Inter, sans-serif';
        ctx.fillText('TOTAL APPS', centerX, centerY + 14);
    }
};

window.addEventListener('themeChanged', () => {
    if (typeof loadAnalytics === 'function') {
        loadAnalytics();
    }
});

document.addEventListener('DOMContentLoaded', () => {
    initTheme();
    initConstellation();

    // Close modal when clicking outside content
    document.querySelectorAll('.modal-overlay').forEach(overlay => {
        overlay.addEventListener('click', (e) => {
            if (e.target === overlay) {
                overlay.style.display = 'none';
            }
        });
    });
});
