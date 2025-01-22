
// main 객체를 통해 모듈화
// 초기화(init)와 저장(save) 기능을 하나의 객체로 캡슐화하여 전역 네임스페이스 오염을 방지
// 코드의 가독성과 재사용성 높임
let main = {
    init : function () {
        // 내부 함수에서 this가 변경되는 문제를 해결하기 위해, 외부의 this 값을 _this 변수에 저장
        // 이벤트 핸들러 안에서 save() 메서드를 호출할 때, 원래의 객체 컨텍스트(main)를 유지
        let _this = this;

        // 이벤트 바인딩
        $('#btn-save').on('click', function () {
            _this.save();
        });
        
    },
    save : function () {
        let data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data) // 객체(data)를 JSON.stringify를 통해 JSON 문자열로 변환

        }).done(function () {   // ajax 응답처리 부
            alert('글이 등록되었습니다.');
            window.location.href = '/';

        }).fail(function (error) {
            alert(JSON.stringify(error));

        })
    }
}

main.init();