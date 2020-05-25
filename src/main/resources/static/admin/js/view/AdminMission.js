import {EVENT_TYPE} from '../../utils/constants.js'

function AdminMission() {

    const initEventListeners = () => {
        $subwayLinesSlider.addEventListener(EVENT_TYPE.CLICK, onDeleteStationHandler)
        $createEdgeButton.addEventListener(EVENT_TYPE.CLICK, onCreateEdgeHandler)
        $subwayLineAddButton.addEventListener(EVENT_TYPE.CLICK, initCreateEdgeForm)
    }

    this.init = () => {
        initEventListeners()
        initSubwayLinesView()
    }
}

const adminMission = new AdminMission()
adminMission.init()
