// 설명. reviwpage
let infoNav = document.getElementById("infoNav");
let reviewNav = document.getElementById("reviewNav");
let infoContents = document.getElementsByClassName("infoContents");
let reviewContainer = document.getElementsByClassName("reviewContainer");
let imgBox = document.getElementsByClassName("imgBox");
infoNav.addEventListener("click", () => {
    infoContents[0].style.display = "block";
    reviewContainer[0].style.display = "none";
    imgBox[0].style.display = "block";
});
reviewNav.addEventListener("click", () => {
    infoContents[0].style.display = "none";
    reviewContainer[0].style.display = "block";
    imgBox[0].style.display = "none";
    fetch(``)
});

// 설명. 지도 사이드바 설정 js
let menuWrap = document.getElementById("menu_wrap");
let dayWrap = document.getElementById("day_wrap");
let infoWrap = document.getElementById("info_wrap");
let homeBtn = document.getElementById("homeBtn");
let myScheduleBtn = document.getElementById("myScheduleBtn");
let imgBtn = document.getElementById("imgBtn");
let BtnBox = document.getElementsByClassName("BtnBox");
let option = document.getElementsByClassName("option");
homeBtn.addEventListener("click", () => {
    console.log(1);

    menuWrap.style.display = "block";
    dayWrap.style.display = "none";
    infoWrap.style.display = "none";
    option[0].style.display = "block";
});

myScheduleBtn.addEventListener("click", () => {
    console.log(2);

    dayWrap.style.display = "block";
    menuWrap.style.display = "none";
    infoWrap.style.display = "none";
    option[0].style.display = "block";
});
imgBtn.addEventListener("click", () => {
    console.log(3);

    infoWrap.style.display = "block";
    menuWrap.style.display = "none";
    dayWrap.style.display = "none";
    BtnBox[0].style.display = "none";
    option[0].style.display = "none";
});

// // 설명. 상세페이지
// let scheduleAdd = document.getElementsByClassName("scheduleAdd");
// let placeItem = document.getElementsByClassName("placeItem");
// let placeList = document.querySelectorAll("#placeList>li");
// scheduleAdd[0].addEventListener("click", () => {
//     dayWrap.style.display = "block";
//     infoWrap.style.display = "none";
//     BtnBox[0].style.display = "block";
//     option[0].style.display = "block";
// });
//
// for (let i = 0; i < placeList.length; i++) {
//     placeItem[i].addEventListener("click", () => {
//         let placeNo = placeList[i].querySelector("#placeNo").innerText; // 3030
//
//         fetch(`/placeQuery/placeInfo?placeNo=${placeNo}`)
//             .then((response) => response.json())
//             .then((data) => {
//                 console.log(data);
//                 let detailPlaceInfo = data.detailPlaceInfo;
//
//                 let placeName = document.getElementById("placeName");
//                 // let categoryName=document.getElementById("categoryName")
//                 let imgPath = document.getElementById("imgPath");
//                 let phoneNumber = document.getElementById("phoneNumber");
//                 let placeAddress = document.getElementById("placeAddress");
//                 let introduction = document.getElementById("introduction");
//
//                 placeName.innerText = detailPlaceInfo.placeName;
//                 let placeLIst = document.querySelectorAll("#placeList>li");
//                 placeLIst.length;
//                 // categoryName.innerText=detailPlaceInfo.categoryName;
//                 phoneNumber.innerText = detailPlaceInfo.phoneNumber;
//                 placeAddress.innerText = detailPlaceInfo.placeAddress;
//                 introduction.innerText = detailPlaceInfo.introduction;
//                 const imgsrc = `<img src="${detailPlaceInfo.imagePath}">`;
//                 imgPath.innerHTML = imgsrc;
//             })
//             .catch((error) => {
//                 console.error(error);
//                 alert("예기치 못한 오류가 발생했습니다.");
//                 infoWrap.style.display = "none";
//                 menuWrap.style.display = "block";
//                 dayWrap.style.display = "none";
//                 option[0].style.display = "block";
//             });
//         infoWrap.style.display = "block";
//         menuWrap.style.display = "none";
//         dayWrap.style.display = "none";
//         option[0].style.display = "none";
//     });
// }
//검색 비동기
let searchBox = document.getElementById("searchBox");
let searchKeyword = document.getElementById("searchKeyword1");
let keyword = document.querySelector(".keyword");
let keywordValue = "";
keyword.addEventListener("keyup", (e) => {
    if (e.keyCode === 13) {
        keywordValue = JSON.stringify(keyword.value);

        fetch(`/placeQuery/searchPlace?searchKeyword=${keywordValue}`)
            .then((response) => response.json())
            .then((json) => {
                json = json.respPlaceList;
                let html = "";
                json.forEach((obj, idx) => {
                    if (!json.length) {
                        console.log("등록된 값이없습니다");
                    } else {
                        console.log(obj);
                        html += `
                        <li class="placeItem">
                    <div class="placeContents">
                    <div class="placeInfo">
                        <p id="placeNo" style="display: none">${obj.placeNo}</p>
                        <h4>${obj.placeName}</h4>
                        <p>${obj.categoryName}</p>
                        <span  class="placeAddr">${obj.placeAddress}</span><br>
                        <span>${obj.phoneNumber}</span>
                    </div>
                    <img src="${obj.imagePath}" alt="">
                    </div>
                <hr>
                </li>`;
                    }
                });
                document.getElementById("placeList").innerHTML = html;
                addPlaceListClickEvent();
            })
            .catch((error) => {
                console.log(error);
            });
    }
});
searchKeyword.addEventListener("click", () => {
    fetch(`/placeQuery/searchPlace?searchKeyword=${keywordValue}`)
        .then((response) => response.json())
        .then((json) => {
            json = json.respPlaceList;
            let html = "";
            json.forEach((obj, idx) => {
                if (!json.length) {
                    console.log("등록된 값이없습니다");
                } else {
                    console.log(obj);
                    html += `
                        <li class="placeItem">
                    <div class="placeContents">
                    <div class="placeInfo">
                        <p id="placeNo" style="display: none">${obj.placeNo}</p>
                        <h4>${obj.placeName}</h4>
                        <p>${obj.categoryName}</p>
                        <span  class="placeAddr">${obj.placeAddress}</span><br>
                        <span>${obj.phoneNumber}</span>
                    </div>
                    <img src="${obj.imagePath}" alt="">
                    </div>
                <hr>
                </li>`;
                }
            });
            document.getElementById("placeList").innerHTML = html;
            addPlaceListClickEvent();
        })
        .catch((error) => {
            console.log(error);
        });
});

function addPlaceListClickEvent() {
    // 설명. 상세페이지
    let scheduleAdd = document.getElementsByClassName("scheduleAdd");
    let placeItem = document.getElementsByClassName("placeItem")
    let placeList = document.querySelectorAll("#placeList>li")
    scheduleAdd[0].addEventListener("click", () => {
        dayWrap.style.display = "block";
        infoWrap.style.display = "none";
        BtnBox[0].style.display = "block";
        option[0].style.display = "block";
    });

    for (let i = 0; i < placeList.length; i++) {
        placeItem[i].addEventListener("click", () => {
            let placeNo = placeList[i].querySelector("#placeNo").innerText; // 3030
            console.log(placeNo);
            fetch(`/placeQuery/placeInfo?placeNo=${placeNo}`)
                .then(response => response.json())
                .then((data) => {
                    console.log(data)
                    let detailPlaceInfo = data.detailPlaceInfo;

                    let placeName = document.getElementById("placeName")
                    // let categoryName=document.getElementById("categoryName")
                    let imgPath = document.getElementById("imgPath")
                    let phoneNumber = document.getElementById("phoneNumber")
                    let placeAddress = document.getElementById("placeAddress")
                    let introduction = document.getElementById("introduction")

                    placeName.innerText = detailPlaceInfo.placeName;
                    // categoryName.innerText=detailPlaceInfo.categoryName;
                    phoneNumber.innerText = detailPlaceInfo.phoneNumber;
                    placeAddress.innerText = detailPlaceInfo.placeAddress;
                    introduction.innerText = detailPlaceInfo.introduction;
                    const imgsrc = `<img src="${detailPlaceInfo.imagePath}">`
                    imgPath.innerHTML = imgsrc;


                })
                .catch((error) => {
                    console.error(error);
                    alert("예기치 못한 오류가 발생했습니다.");
                    infoWrap.style.display = "none";
                    menuWrap.style.display = "block";
                    dayWrap.style.display = "none";
                    option[0].style.display = "block";
                })
            infoWrap.style.display = "block";
            menuWrap.style.display = "none";
            dayWrap.style.display = "none";
            option[0].style.display = "none";
        })
    }
}

// 설명. Btnmouseover
homeBtn.addEventListener("mouseover", () => {
    homeBtn.classList.add("add");
});
homeBtn.addEventListener("mouseleave", () => {
    homeBtn.classList.remove("add");
});

myScheduleBtn.addEventListener("mouseover", () => {
    myScheduleBtn.classList.add("add");
});
myScheduleBtn.addEventListener("mouseleave", () => {
    myScheduleBtn.classList.remove("add");
});

imgBtn.addEventListener("mouseover", () => {
    imgBtn.classList.add("add");
});
imgBtn.addEventListener("mouseleave", () => {
    imgBtn.classList.remove("add");
});
// 설명. userInfoBox

let userImgBox = document.getElementsByClassName("userImgBox");
let hideUserInfoBox = document.getElementsByClassName("hideUserInfoBox");
let hideUserInfoBoxItem = document.querySelectorAll(".hideUserInfoBox>li");

userImgBox[0].addEventListener("click", () => {
    hideUserInfoBox[0].style.display = "block";
});

for (let i = 0; i < hideUserInfoBoxItem.length; i++) {
    hideUserInfoBoxItem[i].addEventListener("mouseover", () => {
        hideUserInfoBoxItem[i].style.backgroundColor = "#70757a";
    });
    hideUserInfoBoxItem[i].addEventListener("mouseleave", () => {
        hideUserInfoBoxItem[i].style.backgroundColor = "transparent";
    });
    hideUserInfoBoxItem[i].addEventListener("click", () => {
        hideUserInfoBox[0].style.display = "none";
    });
}

//내 일정 모달창 설정
let myScheduleModal = document.getElementById("myScheduleModal");
let myScheduleModalBody = document.getElementsByClassName(
    "schedule_modal_body"
);
let scheduleModify = document.getElementsByClassName("scheduleModify");

myScheduleBtn.addEventListener("click", () => {
    myScheduleModal.style.display = "block";
    myScheduleModalBody[0].style.display = "block";

    fetch(`/schedule/query/mySchedule`)
        .then((response) => response.json())
        .then((json) => {
            let html = `  <div><h1>나의 일정</h1></div>`;
            json.forEach((obj, idx) => {
                console.log(json)
                html += `
           
             <div class="plan">
            <div class="plan_title">여행 이름 <span><input type="text" placeholder=${obj.scheduleName}></span></div>
            <div class="plan_date">여행 일자 <span>${obj.startDay}~${obj.endDay}</span></div>
<!--            <div class="plan_cnt">선택장소 <span>12</span></div>-->
            <div class="schedule_modal_btnBox">
                <button class="modal_btn scheduleModify">일정 수정</button>
                <button class="modal_btn">삭제</button>
            </div>
            
                    `
            })
            document.getElementById("schedule_modal_body").innerHTML = html;
        })
        .catch((error) => {
            console.log(error);
        });

    myScheduleModal.addEventListener("click", () => {
        myScheduleModal.style.display = "none";
        myScheduleModalBody[0].style.display = "none";
    });
    scheduleModify[0].addEventListener("click", () => {
        if ((dayWrap.style.display = "none")) {
            dayWrap.style.display = "block";
            infoWrap.style.display = "none";
            menuWrap.style.display = "none";
        }

        myScheduleModal.style.display = "none";
        myScheduleModalBody[0].style.display = "none";
    });

//caleder

})
$(function () {
    $('input[name="datefilter"]').daterangepicker({
        autoUpdateInput: false,
        locale: {
            cancelLabel: "Clear",
        },
    });

    $('input[name="datefilter"]').on(
        "apply.daterangepicker",
        function (ev, picker) {
            $(this).val(
                picker.startDate.format("MM/DD/YYYY") +
                " - " +
                picker.endDate.format("MM/DD/YYYY")
            );

            let startDate =
                picker.startDate._d.getMonth() +
                1 +
                "" +
                picker.startDate._d.getDate() +
                "";

            let endDate =
                picker.endDate._d.getMonth() +
                1 +
                "" +
                picker.endDate._d.getDate() +
                "";
            let result = endDate - startDate;
            console.log(result);
            fetch(``)
                .then((response) => response.json())
                .then((json) => {
                    console.log(json);
                });
        }
    );

    $('input[name="datefilter"]').on(
        "cancel.daterangepicker",
        function (ev, picker) {
            $(this).val("");
        }
    );
});

// 리뷰 불러오기
