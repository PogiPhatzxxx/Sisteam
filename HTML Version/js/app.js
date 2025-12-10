// ASTIG-CAL - Cyberpunk Grade Calculator System
// Based on Java application logic

class GradeCalculator {
    constructor() {
        this.currentUser = null;
        this.currentCourse = null;
        this.courses = ['BSIT', 'BMMA', 'BSIS', 'BSBA', 'BSA', 'BSTM', 'BSHM'];
        this.subjects = this.getSubjectsByCourse();
        this.config = {
            neededGrade: {
                prelimWeight: 0.2,
                midtermWeight: 0.2,
                prefinalWeight: 0.2,
                finalWeight: 0.4,
                cutOffGrade: 59.50  // Default for non-BSIT/BSA courses
            },
            gwa: {
                prelimWeight: 0.2,
                midtermWeight: 0.2,
                prefinalWeight: 0.2,
                finalWeight: 0.4,
                passingGrade: 59.50
            },
            exam: {
                examMaxScore: 70,
                examPercentage: 50  // Default for non-BSA courses
            }
        };
        this.history = {
            neededGrade: [],
            exam: []
        };
        this.gwaCourses = [];
        this.instructionPreferences = this.loadInstructionPreferences();
        
        this.init();
    }

    init() {
        this.setupEventListeners();
        this.updateCourseConfig();
        
        // For testing: Uncomment the next line to clear instruction preferences on load
        // this.resetInstructionPreferences();
    }

    // Instruction Preferences Management
    loadInstructionPreferences() {
        const saved = localStorage.getItem('instructionPreferences');
        if (saved) {
            const prefs = JSON.parse(saved);
            // Debug: log the loaded preferences
            console.log('Loaded instruction preferences:', prefs);
            return prefs;
        }
        // Default: show all instructions (false means don't hide)
        const defaults = {
            neededGrade: false,
            gwa: false,
            exam: false
        };
        console.log('Using default instruction preferences:', defaults);
        return defaults;
    }

    saveInstructionPreferences() {
        localStorage.setItem('instructionPreferences', JSON.stringify(this.instructionPreferences));
    }

    setInstructionPreference(calculator, hide) {
        this.instructionPreferences[calculator] = hide;
        this.saveInstructionPreferences();
    }

    shouldShowInstructions(calculator) {
        const shouldShow = !this.instructionPreferences[calculator];
        console.log(`Should show ${calculator} instructions:`, shouldShow);
        return shouldShow;
    }

    // Method to reset all instruction preferences (for testing)
    resetInstructionPreferences() {
        this.instructionPreferences = {
            neededGrade: false,
            gwa: false,
            exam: false
        };
        this.saveInstructionPreferences();
        console.log('Instruction preferences reset to defaults');
    }

    // Subject Management (from SubjectManager.java)
    getSubjectsByCourse(course = null) {
        const subjects = {
            'BSIT': [
                'Data Structures and Algorithms',
                'Human-Computer Interaction',
                'Object-Oriented Programming',
                'P.E./PATHFIT 3: Individual-Dual Sports',
                'Platform Technology (Operating Systems)',
                'Principles of Communication',
                'Readings in Philippine History',
                'Rizal\'s Life and Works'
            ],
            'BMMA': [
                'BMMA Subject 1', 'BMMA Subject 2', 'BMMA Subject 3',
                'BMMA Subject 4', 'BMMA Subject 5', 'BMMA Subject 6',
                'BMMA Subject 7', 'BMMA Subject 8'
            ],
            'BSIS': [
                'BSIS Subject 1', 'BSIS Subject 2', 'BSIS Subject 3',
                'BSIS Subject 4', 'BSIS Subject 5', 'BSIS Subject 6',
                'BSIS Subject 7', 'BSIS Subject 8'
            ],
            'BSBA': [
                'BSBA Subject 1', 'BSBA Subject 2', 'BSBA Subject 3',
                'BSBA Subject 4', 'BSBA Subject 5', 'BSBA Subject 6',
                'BSBA Subject 7', 'BSBA Subject 8'
            ],
            'BSA': [
                'BSA Subject 1', 'BSA Subject 2', 'BSA Subject 3',
                'BSA Subject 4', 'BSA Subject 5', 'BSA Subject 6',
                'BSA Subject 7', 'BSA Subject 8'
            ],
            'BSTM': [
                'BSTM Subject 1', 'BSTM Subject 2', 'BSTM Subject 3',
                'BSTM Subject 4', 'BSTM Subject 5', 'BSTM Subject 6',
                'BSTM Subject 7', 'BSTM Subject 8'
            ],
            'BSHM': [
                'BSHM Subject 1', 'BSHM Subject 2', 'BSHM Subject 3',
                'BSHM Subject 4', 'BSHM Subject 5', 'BSHM Subject 6',
                'BSHM Subject 7', 'BSHM Subject 8'
            ]
        };

        if (!course) {
            return subjects;
        }
        return subjects[course] || [];
    }

    updateCourseConfig() {
        // Update configuration based on course
        if (this.currentCourse === 'BSIT') {
            this.config.neededGrade.cutOffGrade = 70.50;
            this.config.exam.examPercentage = 50;
        } else if (this.currentCourse === 'BSA') {
            this.config.neededGrade.cutOffGrade = 76.00;
            this.config.exam.examPercentage = 80;
        } else {
            // Default for all other courses (BMMA, BSIS, BSBA, BSTM, BSHM)
            this.config.neededGrade.cutOffGrade = 59.50;
            this.config.exam.examPercentage = 50;
        }
    }

    setupEventListeners() {
        // Login functionality
        document.getElementById('loginBtn').addEventListener('click', () => this.handleLogin());
        
        // Keyboard shortcut to reset instruction preferences (Ctrl+Shift+R)
        document.addEventListener('keydown', (e) => {
            if (e.ctrlKey && e.shiftKey && e.key === 'R') {
                e.preventDefault();
                this.resetInstructionPreferences();
                this.showMessage('Instruction preferences reset!', 'success');
            }
        });

        // Navigation
        document.querySelectorAll('.nav-btn[data-page]').forEach(btn => {
            btn.addEventListener('click', (e) => {
                const page = e.currentTarget.dataset.page;
                this.navigateToCalculator(page);
            });
        });

        // Calculator cards
        document.querySelectorAll('.calc-card').forEach(card => {
            card.addEventListener('click', (e) => {
                const calc = e.currentTarget.dataset.calc;
                this.navigateToCalculator(calc);
            });
        });

        // Needed Grade Calculator
        document.getElementById('ngCalculate').addEventListener('click', () => this.calculateNeededGrade());

        // GWA Calculator
        document.getElementById('clearAllCourses').addEventListener('click', () => this.clearAllCourses());
        document.getElementById('addCourse').addEventListener('click', () => this.addCoursePanel());
        document.getElementById('clearAllCourses').addEventListener('click', () => this.clearAllCourses());

        // Exam Calculator
        document.getElementById('examCalculate').addEventListener('click', () => this.calculateExamGrade());

        // Configuration
        document.querySelectorAll('.config-btn').forEach(btn => {
            btn.addEventListener('click', (e) => {
                const page = this.getCurrentPage();
                this.openConfigModal(page);
            });
        });

        // Modal close
        document.querySelector('.close-btn').addEventListener('click', () => this.closeConfigModal());
        document.getElementById('saveConfig').addEventListener('click', () => this.saveConfiguration());

        // Input field clearing on focus
        document.querySelectorAll('.cyber-input').forEach(input => {
            input.addEventListener('focus', (e) => {
                if (e.target.value === '0' || e.target.placeholder === 'Enter here') {
                    e.target.value = '';
                }
            });
        });
    }

    handleLogin() {
        let course = document.getElementById('course').value;
        const username = document.getElementById('username').value.trim();
        const idnumber = document.getElementById('idnumber').value.trim();

        // Validation (from LoginFrame.java)
        if (!course) {
            this.showMessage('Please Select Course', 'error');
            return;
        }
        if (!username) {
            this.showMessage('Please Enter Username', 'error');
            return;
        }
        if (!idnumber) {
            this.showMessage('Please Enter ID', 'error');
            return;
        }

        // No custom course handling needed anymore

        // Set user data
        this.currentUser = username;
        this.currentCourse = course;
        this.updateCourseConfig();

        // Update main menu display
        document.getElementById('displayUsername').textContent = username;
        document.getElementById('displayId').textContent = `ID: ${idnumber}`;

        // Show welcome message
        this.showMessage(`Welcome : ${username}`, 'success');

        // Show disclaimer after login
        setTimeout(() => {
            this.showDisclaimer();
        }, 1000);

        // Navigate to main menu
        this.showPage('mainMenuPage');
        this.updateSubjectSelects();
    }

    updateSubjectSelects() {
        const selects = ['ngSubject', 'examSubject'];
        
        selects.forEach(selectId => {
            const select = document.getElementById(selectId);
            if (select) {
                select.innerHTML = '<option value="">Enter Subject Name Below</option>';
                // Add custom option
                const customOption = document.createElement('option');
                customOption.value = 'custom';
                customOption.textContent = '+ Enter Custom Subject';
                select.appendChild(customOption);
                select.style.display = 'block';
                
                // Show custom input by default and hide select
                const customInput = document.getElementById(selectId.replace('Subject', 'CustomSubject'));
                if (customInput) {
                    customInput.style.display = 'block';
                    customInput.value = '';
                    customInput.placeholder = 'Enter subject name here';
                    select.style.display = 'none';
                }
            }
        });
    }

    navigateToCalculator(calc) {
        console.log('Navigating to calculator:', calc);
        const pageMap = {
            'mainMenu': 'mainMenuPage',
            'needed': 'neededCalcPage',
            'gwa': 'gwaCalcPage',
            'exam': 'examCalcPage'
        };

        const targetPage = pageMap[calc];
        if (targetPage) {
            this.showPage(targetPage);
            
            // Show instructions for calculators only if not hidden
            if (calc === 'needed' && this.shouldShowInstructions('neededGrade')) {
                console.log('Showing needed grade instructions');
                setTimeout(() => {
                    this.showNeededGradeInstructions();
                }, 500);
            } else if (calc === 'gwa' && this.shouldShowInstructions('gwa')) {
                console.log('Showing GWA instructions');
                setTimeout(() => {
                    this.showGWAInstructions();
                }, 500);
            } else if (calc === 'exam' && this.shouldShowInstructions('exam')) {
                console.log('Showing exam instructions');
                setTimeout(() => {
                    this.showExamInstructions();
                }, 500);
            } else {
                console.log(`Not showing instructions for ${calc} - preference is set to hide`);
            }
        }
    }

    showPage(pageId) {
        document.querySelectorAll('.page').forEach(page => {
            page.classList.remove('active');
        });
        document.getElementById(pageId).classList.add('active');
    }

    getCurrentPage() {
        const activePage = document.querySelector('.page.active');
        return activePage ? activePage.id.replace('Page', '') : '';
    }

    // Needed Grade Calculator (from NeededGradeCalculator.java)
    calculateNeededGrade() {
        const prelim = parseFloat(document.getElementById('ngPrelim').value) || 0;
        const midterm = parseFloat(document.getElementById('ngMidterm').value) || 0;
        const prefinal = parseFloat(document.getElementById('ngPrefinal').value) || 0;
        const final = parseFloat(document.getElementById('ngFinal').value) || 0;
        
        let subject = document.getElementById('ngCustomSubject').value || 'Custom Subject';

        // All fields now have default value of 0, so no need to check for empty fields
        // The parseFloat() || 0 handles any edge cases

        const config = this.config.neededGrade;
        const target = config.cutOffGrade;

        // If all grades are entered, calculate final GWA
        if (prelim > 0 && midterm > 0 && prefinal > 0 && final > 0) {
            const gwa = (prelim * config.prelimWeight) + 
                       (midterm * config.midtermWeight) + 
                       (prefinal * config.prefinalWeight) + 
                       (final * config.finalWeight);

            if (gwa >= target) {
                this.showMessage(`Your Final GWA is: ${gwa.toFixed(2)}\n\nCongratulations! You have PASSED this subject!`, 'success');
            } else {
                this.showMessage(`Your Final GWA is: ${gwa.toFixed(2)}\n\nYou have FAILED this subject. Whahahaha`, 'error');
            }
            return;
        }

        // Calculate needed grades for remaining terms
        let completed = 0;
        if (prelim > 0) completed += prelim * config.prelimWeight;
        if (midterm > 0) completed += midterm * config.midtermWeight;
        if (prefinal > 0) completed += prefinal * config.prefinalWeight;

        const missing = [prelim === 0, midterm === 0, prefinal === 0].filter(Boolean).length;
        const remainingWeight = (missing * config.prelimWeight) + config.finalWeight;
        const neededAverage = (target - completed) / remainingWeight;

        let message = 'You need at least:\n';
        if (prelim === 0) message += `${neededAverage.toFixed(2)} in Prelim\n`;
        if (midterm === 0) message += `${neededAverage.toFixed(2)} in Midterm\n`;
        if (prefinal === 0) message += `${neededAverage.toFixed(2)} in Pre-Final\n`;
        if (final === 0) message += `${neededAverage.toFixed(2)} in Final\n`;

        // Motivational messages (from Java logic)
        let motivationalMessage = '';
        if (neededAverage > 100) {
            motivationalMessage = 'Bagsak Kana Boiii';
        } else if (neededAverage >= 90) {
            motivationalMessage = 'Du Alanganin Na Haw';
        } else if (neededAverage >= 80) {
            motivationalMessage = 'Kaya Mo Yan Laban Lang';
        } else if (neededAverage >= 70) {
            motivationalMessage = 'Sus Basic Lang ni Saimo Ah';
        } else if (neededAverage >= 60) {
            motivationalMessage = 'Gwapooo, Gamay Gamay lang Lagson mo Syaro ma Bagsak';
        }

        if (motivationalMessage) {
            this.showMessage(motivationalMessage, 'info');
        }

        this.showMessage(message, 'info');

        // Add to history
        this.addToHistory('neededGrade', subject, neededAverage.toFixed(2));
    }


    calculateGWA() {
        let totalUnits = 0;
        let totalWeighted = 0;

        this.gwaCourses.forEach((course, index) => {
            const coursePanel = document.getElementById(`course-${index}`);
            if (coursePanel) {
                const units = parseFloat(coursePanel.querySelector('.units-input').value) || 0;
                const prelim = parseFloat(coursePanel.querySelector('.prelim-input').value) || 0;
                const midterm = parseFloat(coursePanel.querySelector('.midterm-input').value) || 0;
                const prefinal = parseFloat(coursePanel.querySelector('.prefinal-input').value) || 0;
                const final = parseFloat(coursePanel.querySelector('.final-input').value) || 0;

                const avg = (prelim * this.config.gwa.prelimWeight) + 
                           (midterm * this.config.gwa.midtermWeight) + 
                           (prefinal * this.config.gwa.prefinalWeight) + 
                           (final * this.config.gwa.finalWeight);

                // Update average display
                const avgLabel = coursePanel.querySelector('.avg-display');
                const avgLabelMobile = coursePanel.querySelector('.avg-display-mobile');
                if (avgLabel) {
                    avgLabel.textContent = `AVG: ${avg.toFixed(2)}%`;
                }
                if (avgLabelMobile) {
                    avgLabelMobile.textContent = `AVG: ${avg.toFixed(2)}%`;
                }

                totalUnits += units;
                totalWeighted += avg * units;
            }
        });

        document.getElementById('unitsValue').textContent = Math.round(totalUnits);

        if (totalUnits > 0) {
            const gwa = totalWeighted / totalUnits;
            document.getElementById('gwaValue').textContent = `${gwa.toFixed(2)}%`;
            
            const statusElement = document.getElementById('statusValue');
            if (gwa >= 59.50) {
                statusElement.textContent = 'PASSING';
                statusElement.style.color = '#00ff88';
            } else {
                statusElement.textContent = 'FAILING';
                statusElement.style.color = '#ff0040';
            }
        } else {
            document.getElementById('gwaValue').textContent = '00.00%';
            document.getElementById('statusValue').textContent = '-';
        }
    }

    addCoursePanel() {
        const courseId = `course-${this.gwaCourses.length}`;
        const coursePanel = document.createElement('div');
        coursePanel.className = 'course-panel';
        coursePanel.id = courseId;

        coursePanel.innerHTML = `
            <button class="collapse-toggle collapse-top-left" onclick="calculator.toggleCourseCollapse('${courseId}')">
                <span class="collapse-icon">▼</span>
            </button>
            <button class="remove-course" onclick="calculator.removeCoursePanel('${courseId}')">×</button>
            <div class="avg-display-mobile">AVG: 00.00%</div>
            <div class="course-panel-header">
                <input type="text" class="cyber-input custom-subject-input" placeholder="Enter subject name here">
                <input type="number" class="cyber-input units-input" placeholder="Units" min="1" max="20">
                <div class="avg-display">AVG: 00.00%</div>
            </div>
            <div class="course-content" id="${courseId}-content">
                <div class="course-grades">
                    <input type="number" class="cyber-input prelim-input" placeholder="Prelim" min="0" max="100">
                    <input type="number" class="cyber-input midterm-input" placeholder="Midterm" min="0" max="100">
                    <input type="number" class="cyber-input prefinal-input" placeholder="Prefinal" min="0" max="100">
                    <input type="number" class="cyber-input final-input" placeholder="Final" min="0" max="100">
                </div>
            </div>
        `;

        document.getElementById('coursesList').appendChild(coursePanel);
        this.gwaCourses.push(coursePanel);
        
        // Add event listeners for real-time AVG calculation
        const gradeInputs = coursePanel.querySelectorAll('.prelim-input, .midterm-input, .prefinal-input, .final-input');
        gradeInputs.forEach(input => {
            input.addEventListener('input', () => this.calculateGWA());
        });
        
        // Handle units input change
        const unitsInput = coursePanel.querySelector('.units-input');
        unitsInput.addEventListener('input', () => this.calculateGWA());
    }

    removeCoursePanel(courseId) {
        const panel = document.getElementById(courseId);
        if (panel) {
            panel.remove();
            this.gwaCourses = this.gwaCourses.filter(course => course.id !== courseId);
            this.calculateGWA(); // Recalculate
        }
    }

    clearAllCourses() {
        if (this.gwaCourses.length === 0) {
            this.showMessage('No courses to clear', 'info');
            return;
        }
        
        if (confirm('Are you sure you want to clear all courses?')) {
            document.getElementById('coursesList').innerHTML = '';
            this.gwaCourses = [];
            this.calculateGWA(); // Reset GWA display
            this.showMessage('All courses cleared', 'success');
        }
    }

    toggleCourseCollapse(courseId) {
        const coursePanel = document.getElementById(courseId);
        if (coursePanel) {
            coursePanel.classList.toggle('collapsed');
        }
    }

    // Exam Calculator (from ExamCalculator.java)
    calculateExamGrade() {
        const score = parseFloat(document.getElementById('examScore').value);
        
        let subject = document.getElementById('examCustomSubject').value || 'Custom Subject';

        if (isNaN(score)) {
            this.showMessage('Please enter a valid exam score', 'error');
            return;
        }

        const config = this.config.exam;
        const result = (score / config.examMaxScore) * config.examPercentage;

        document.getElementById('examResult').textContent = result.toFixed(2);

        if (result < 30) {
            this.showMessage('Keep it Up Bro, You can still Pass!', 'info');
        }

        // Add to history
        this.addToHistory('exam', subject, result.toFixed(2));
    }

    // History Management
    addToHistory(type, subject, result) {
        this.history[type].push({ subject, result, timestamp: new Date() });
        
        const historyElement = type === 'neededGrade' ? 
            document.getElementById('ngHistory') : 
            document.getElementById('examHistory');

        if (historyElement) {
            const historyItem = document.createElement('div');
            historyItem.className = 'history-item';
            historyItem.innerHTML = `
                <div class="history-subject">${subject}</div>
                <div class="history-result">${result}%</div>
            `;
            historyElement.insertBefore(historyItem, historyElement.firstChild);
        }
    }

    // Configuration Management
    openConfigModal(page) {
        const modal = document.getElementById('configModal');
        const configForm = document.getElementById('configForm');
        const configTitle = document.getElementById('configTitle');

        let formHTML = '';

        switch(page) {
            case 'neededCalc':
                configTitle.textContent = 'NEEDED GRADE CONFIG';
                formHTML = `
                    <div class="config-input-group">
                        <label>Cut-Off Grade:</label>
                        <input type="number" id="configCutOff" value="${this.config.neededGrade.cutOffGrade}" step="0.01">
                    </div>
                    <div class="config-input-group">
                        <label>Prelim Weight:</label>
                        <input type="number" id="configPrelim" value="${this.config.neededGrade.prelimWeight}" step="0.01">
                    </div>
                    <div class="config-input-group">
                        <label>Midterm Weight:</label>
                        <input type="number" id="configMidterm" value="${this.config.neededGrade.midtermWeight}" step="0.01">
                    </div>
                    <div class="config-input-group">
                        <label>Prefinal Weight:</label>
                        <input type="number" id="configPrefinal" value="${this.config.neededGrade.prefinalWeight}" step="0.01">
                    </div>
                    <div class="config-input-group">
                        <label>Final Weight:</label>
                        <input type="number" id="configFinal" value="${this.config.neededGrade.finalWeight}" step="0.01">
                    </div>
                `;
                break;
            case 'gwaCalc':
                configTitle.textContent = 'GWA CONFIG';
                formHTML = `
                    <div class="config-input-group">
                        <label>Passing Grade:</label>
                        <input type="number" id="configPassingGrade" value="${this.config.gwa.passingGrade}" step="0.01">
                    </div>
                    <div class="config-input-group">
                        <label>Prelim Weight:</label>
                        <input type="number" id="configPrelim" value="${this.config.gwa.prelimWeight}" step="0.01">
                    </div>
                    <div class="config-input-group">
                        <label>Midterm Weight:</label>
                        <input type="number" id="configMidterm" value="${this.config.gwa.midtermWeight}" step="0.01">
                    </div>
                    <div class="config-input-group">
                        <label>Prefinal Weight:</label>
                        <input type="number" id="configPrefinal" value="${this.config.gwa.prefinalWeight}" step="0.01">
                    </div>
                    <div class="config-input-group">
                        <label>Final Weight:</label>
                        <input type="number" id="configFinal" value="${this.config.gwa.finalWeight}" step="0.01">
                    </div>
                `;
                break;
            case 'examCalc':
                configTitle.textContent = 'EXAM CONFIG';
                formHTML = `
                    <div class="config-input-group">
                        <label>Max Exam Score:</label>
                        <input type="number" id="configMaxScore" value="${this.config.exam.examMaxScore}" step="1">
                    </div>
                    <div class="config-input-group">
                        <label>Exam Percentage:</label>
                        <input type="number" id="configExamPercentage" value="${this.config.exam.examPercentage}" step="1">
                    </div>
                `;
                break;
        }

        configForm.innerHTML = formHTML;
        modal.classList.add('active');
    }

    closeConfigModal() {
        document.getElementById('configModal').classList.remove('active');
    }

    saveConfiguration() {
        const page = this.getCurrentPage();

        switch(page) {
            case 'neededCalc':
                this.config.neededGrade.cutOffGrade = parseFloat(document.getElementById('configCutOff').value);
                this.config.neededGrade.prelimWeight = parseFloat(document.getElementById('configPrelim').value);
                this.config.neededGrade.midtermWeight = parseFloat(document.getElementById('configMidterm').value);
                this.config.neededGrade.prefinalWeight = parseFloat(document.getElementById('configPrefinal').value);
                this.config.neededGrade.finalWeight = parseFloat(document.getElementById('configFinal').value);
                break;
            case 'gwaCalc':
                this.config.gwa.passingGrade = parseFloat(document.getElementById('configPassingGrade').value);
                this.config.gwa.prelimWeight = parseFloat(document.getElementById('configPrelim').value);
                this.config.gwa.midtermWeight = parseFloat(document.getElementById('configMidterm').value);
                this.config.gwa.prefinalWeight = parseFloat(document.getElementById('configPrefinal').value);
                this.config.gwa.finalWeight = parseFloat(document.getElementById('configFinal').value);
                break;
            case 'examCalc':
                this.config.exam.examMaxScore = parseFloat(document.getElementById('configMaxScore').value);
                this.config.exam.examPercentage = parseFloat(document.getElementById('configExamPercentage').value);
                break;
        }

        this.showMessage('Configuration Saved!', 'success');
        this.closeConfigModal();
    }

    // Needed Grade Calculator Instructions
    showNeededGradeInstructions() {
        const instructionsModal = document.createElement('div');
        instructionsModal.className = 'message-modal instructions-modal';
        instructionsModal.innerHTML = `
            <div class="message-content instructions-content">
                <div class="instructions-header">
                    <h3>NEEDED GRADE CALCULATOR</h3>
                </div>
                <div class="instructions-text">
                    <div class="instruction-section">
                        <h4>SUBJECT INPUT</h4>
                        <p><strong>Subject field is OPTIONAL</strong> - You can leave it blank or enter any subject name.</p>
                    </div>
                    
                    <div class="instruction-section">
                        <h4>HOW IT WORKS</h4>
                        <div class="scenario-list">
                            <div class="scenario">
                                <span class="scenario-icon">1️</span>
                                <p><strong>Prelim only:</strong> Shows needed grades for Midterm, Pre-Final & Final</p>
                            </div>
                            <div class="scenario">
                                <span class="scenario-icon">2️</span>
                                <p><strong>Prelim + Midterm:</strong> Shows needed grades for Pre-Final & Final</p>
                            </div>
                            <div class="scenario">
                                <span class="scenario-icon">3️</span>
                                <p><strong>Prelim + Midterm + Pre-Final:</strong> Shows needed grade for Final</p>
                            </div>
                            <div class="scenario">
                                <span class="scenario-icon">4️</span>
                                <p><strong>All fields filled:</strong> Shows your final average for the subject</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="instruction-section">
                        <h4>CONFIGURATION</h4>
                        <p>Use the <strong>CONFIG button</strong> to adjust:</p>
                        <ul class="config-list">
                            <li>Cut-off grade requirements</li>
                            <li>Weight percentages for each term</li>
                            <li>Passing thresholds</li>
                        </ul>
                    </div>
                </div>
                <div class="instructions-buttons">
                    <button class="instructions-got-it">Got It!</button>
                    <div class="instructions-checkbox-container">
                        <input type="checkbox" id="neededGradeHideInstructions" class="instructions-checkbox">
                        <label for="neededGradeHideInstructions" class="instructions-checkbox-label">Do not show it later</label>
                    </div>
                </div>
            </div>
        `;

        const gotItBtn = instructionsModal.querySelector('.instructions-got-it');
        const hideCheckbox = instructionsModal.querySelector('#neededGradeHideInstructions');
        
        // Reset checkbox to unchecked on modal creation
        hideCheckbox.checked = false;
        
        gotItBtn.addEventListener('click', () => {
            if (hideCheckbox.checked) {
                this.setInstructionPreference('neededGrade', true);
            }
            document.body.removeChild(instructionsModal);
        });

        // Also close on backdrop click
        instructionsModal.addEventListener('click', (e) => {
            if (e.target === instructionsModal) {
                document.body.removeChild(instructionsModal);
            }
        });

        document.body.appendChild(instructionsModal);
    }

    // GWA Calculator Instructions
    showGWAInstructions() {
        const gwaInstructionsModal = document.createElement('div');
        gwaInstructionsModal.className = 'message-modal gwa-instructions-modal';
        gwaInstructionsModal.innerHTML = `
            <div class="message-content gwa-instructions-content">
                <div class="gwa-instructions-header">
                    <h3>GWA CALCULATOR</h3>
                </div>
                <div class="gwa-instructions-text">
                    <div class="gwa-instruction-section">
                        <h4>ADDING COURSES</h4>
                        <p>You can <strong>add multiple courses or subjects</strong> using the "ADD COURSE" button. Each course will have its own calculation panel.</p>
                    </div>
                    
                    <div class="gwa-instruction-section">
                        <h4>REQUIRED INPUTS</h4>
                        <div class="gwa-input-list">
                            <div class="gwa-input-item">
                                <span class="input-icon"></span>
                                <p><strong>Subject Name:</strong> Enter the course/subject name</p>
                            </div>
                            <div class="gwa-input-item">
                                <span class="input-icon"></span>
                                <p><strong>Units:</strong> Enter how many units the subject has</p>
                            </div>
                            <div class="gwa-input-item">
                                <span class="input-icon"></span>
                                <p><strong>Grades:</strong> Enter your grades for Prelim, Midterm, Pre-Final, and Final</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="gwa-instruction-section">
                        <h4>RESULTS EXPLAINED</h4>
                        <div class="gwa-results-list">
                            <div class="gwa-result-item">
                                <span class="result-icon"></span>
                                <p><strong>GWA (above):</strong> Your overall General Weighted Average across ALL subjects added</p>
                            </div>
                            <div class="gwa-result-item">
                                <span class="result-icon"></span>
                                <p><strong>AVG in course:</strong> Your average grade for that specific subject only</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="gwa-instruction-section">
                        <h4>CONFIGURATION</h4>
                        <p>Use the <strong>CONFIG button</strong> to adjust:</p>
                        <ul class="gwa-config-list">
                            <li>Passing grade requirements</li>
                            <li>Weight percentages for each term</li>
                            <li>Grade calculation parameters</li>
                        </ul>
                    </div>
                </div>
                <div class="gwa-instructions-buttons">
                    <button class="gwa-instructions-got-it">Got It!</button>
                    <div class="gwa-instructions-checkbox-container">
                        <input type="checkbox" id="gwaHideInstructions" class="gwa-instructions-checkbox">
                        <label for="gwaHideInstructions" class="gwa-instructions-checkbox-label">Do not show it later</label>
                    </div>
                </div>
            </div>
        `;

        const gotItBtn = gwaInstructionsModal.querySelector('.gwa-instructions-got-it');
        const hideCheckbox = gwaInstructionsModal.querySelector('#gwaHideInstructions');
        
        // Reset checkbox to unchecked on modal creation
        hideCheckbox.checked = false;
        
        gotItBtn.addEventListener('click', () => {
            if (hideCheckbox.checked) {
                this.setInstructionPreference('gwa', true);
            }
            document.body.removeChild(gwaInstructionsModal);
        });

        // Also close on backdrop click
        gwaInstructionsModal.addEventListener('click', (e) => {
            if (e.target === gwaInstructionsModal) {
                document.body.removeChild(gwaInstructionsModal);
            }
        });

        document.body.appendChild(gwaInstructionsModal);
    }

    // Exam Calculator Instructions
    showExamInstructions() {
        const examInstructionsModal = document.createElement('div');
        examInstructionsModal.className = 'message-modal exam-instructions-modal';
        examInstructionsModal.innerHTML = `
            <div class="message-content exam-instructions-content">
                <div class="exam-instructions-header">
                    <h3>EXAM CALCULATOR</h3>
                </div>
                <div class="exam-instructions-text">
                    <div class="exam-instruction-section">
                        <h4>SUBJECT INPUT</h4>
                        <p><strong>Subject Name is OPTIONAL</strong> - You can leave it blank or enter any subject name.</p>
                    </div>
                    
                    <div class="exam-instruction-section">
                        <h4>EXAM SCORE</h4>
                        <div class="exam-input-section">
                            <div class="exam-input-item">
                                <span class="exam-icon"></span>
                                <p><strong>Enter your Exam Score:</strong> Input the score you got on your exam</p>
                            </div>
                            <div class="exam-result-item">
                                <span class="result-icon"></span>
                                <p><strong>Result:</strong> Shows your Initial Grade for that exam</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="exam-instruction-section">
                        <h4>CONFIGURATION</h4>
                        <p>Use the <strong>CONFIG button</strong> to adjust:</p>
                        <ul class="exam-config-list">
                            <li>Maximum exam score</li>
                            <li>Exam percentage weight</li>
                            <li>Grade calculation parameters</li>
                        </ul>
                    </div>
                </div>
                <div class="exam-instructions-buttons">
                    <button class="exam-instructions-got-it">Got It!</button>
                    <div class="exam-instructions-checkbox-container">
                        <input type="checkbox" id="examHideInstructions" class="exam-instructions-checkbox">
                        <label for="examHideInstructions" class="exam-instructions-checkbox-label">Do not show it later</label>
                    </div>
                </div>
            </div>
        `;

        const gotItBtn = examInstructionsModal.querySelector('.exam-instructions-got-it');
        const hideCheckbox = examInstructionsModal.querySelector('#examHideInstructions');
        
        // Reset checkbox to unchecked on modal creation
        hideCheckbox.checked = false;
        
        gotItBtn.addEventListener('click', () => {
            if (hideCheckbox.checked) {
                this.setInstructionPreference('exam', true);
            }
            document.body.removeChild(examInstructionsModal);
        });

        examInstructionsModal.addEventListener('click', (e) => {
            if (e.target === examInstructionsModal) {
                document.body.removeChild(examInstructionsModal);
            }
        });

        document.body.appendChild(examInstructionsModal);
    }


    showDisclaimer() {
        const disclaimerModal = document.createElement('div');
        disclaimerModal.className = 'message-modal disclaimer-modal';
        disclaimerModal.innerHTML = `
            <div class="message-content disclaimer-content">
                <div class="disclaimer-header">
                    <h3>IMPORTANT DISCLAIMER</h3>
                </div>
                <div class="disclaimer-text">
                    <p><strong>This Project is NOT Officially affiliated with STI School.</strong></p>
                    <p>Results shown are <strong>ESTIMATIONS ONLY</strong> and not official grades.</p>
                    <p>The formulas used are based on research and surveys conducted within our school.</p>
                    <p><em>Please refer to your official school records for accurate grade information.</em></p>
                </div>
                <div class="disclaimer-buttons">
                    <button class="disclaimer-accept">I Understand</button>
                </div>
            </div>
        `;

        const acceptBtn = disclaimerModal.querySelector('.disclaimer-accept');
        acceptBtn.addEventListener('click', () => {
            document.body.removeChild(disclaimerModal);
        });

        // Also close on backdrop click
        disclaimerModal.addEventListener('click', (e) => {
            if (e.target === disclaimerModal) {
                document.body.removeChild(disclaimerModal);
            }
        });

        document.body.appendChild(disclaimerModal);
    }

    // Utility Functions
    showMessage(message, type = 'info') {
        // Create custom modal instead of alert for better cyberpunk feel
        const messageModal = document.createElement('div');
        messageModal.className = 'message-modal';
        messageModal.innerHTML = `
            <div class="message-content">
                <div class="message-text">${message.replace(/\n/g, '<br>')}</div>
                <button class="message-close">OK</button>
            </div>
        `;

        // Style based on type
        const colors = {
            success: '#00ff88',
            error: '#ff0040',
            info: '#00d4ff'
        };

        messageModal.style.cssText = `
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.9);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 2000;
            animation: slide-in 0.3s ease;
        `;

        const content = messageModal.querySelector('.message-content');
        content.style.cssText = `
            background: var(--dark-secondary);
            border: 2px solid ${colors[type]};
            border-radius: 10px;
            padding: 2rem;
            max-width: 400px;
            text-align: center;
        `;

        const text = messageModal.querySelector('.message-text');
        text.style.cssText = `
            color: var(--text-primary);
            margin-bottom: 1.5rem;
            line-height: 1.6;
        `;

        const closeBtn = messageModal.querySelector('.message-close');
        closeBtn.style.cssText = `
            background: ${colors[type]};
            border: none;
            color: var(--dark-bg);
            padding: 10px 20px;
            font-family: 'Orbitron', sans-serif;
            font-weight: 600;
            cursor: pointer;
            text-transform: uppercase;
        `;

        closeBtn.addEventListener('click', () => {
            document.body.removeChild(messageModal);
        });

        document.body.appendChild(messageModal);
    }
}

// Initialize the application
const calculator = new GradeCalculator();

// Add initial course panel for GWA calculator
document.addEventListener('DOMContentLoaded', () => {
    calculator.addCoursePanel();
});anel();